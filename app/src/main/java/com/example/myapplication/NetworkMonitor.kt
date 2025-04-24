/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.shareIn

/**
 * Provides a long-living [SharedFlow] that represents the device's current
 * network status given the defined required capabilities.
 *
 * This is provided in the Application and will be lazily initialized to prevent it from being
 * created before it is needed, but will live as a singleton for the life of the application once
 * initialized.
 *
 * Will emit a value immediately of the current network status, and then re-emit on every network
 * condition change. Will replay the most recent value for new subscribers. Will stop emitting after
 * a brief delay when all subscribers have unsubscribed.
 *
 * @property context This expects to receive the ActivityContext for the current Photopicker
 *   session.
 * @property scope The [CoroutineScope] that the [NetworkStatus] flow will share in.
 */
class NetworkMonitor(context: Context, private val scope: CoroutineScope) {
    companion object {
        val TAG = "NetworkMonitor"
        val STOP_TIMEOUT_MILLIS: Long = 60000L // One minute
    }
    private val connectivityManager: ConnectivityManager = context.requireSystemService()

    val networkStatus: SharedFlow<NetworkStatus> =
        callbackFlow<NetworkStatus> {
            val networkStatusCallback =
                object : ConnectivityManager.NetworkCallback() {
                    override fun onUnavailable() {
                        trySend(NetworkStatus.Unavailable).onFailure {
                            Log.e(TAG, """Failed to notify downstream onUnavailable.""")
                        }
                    }

                    override fun onAvailable(network: Network) {
                        trySend(NetworkStatus.Available).onFailure {
                            Log.e(TAG, """Failed to notify downstream onAvailable.""")
                        }
                    }

                    override fun onLost(network: Network) {
                        trySend(NetworkStatus.Unavailable).onFailure {
                            Log.e(TAG, """Failed to notify downstream onLost.""")
                        }
                    }
                }

            val request =
                NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()

            val isConnected =
                connectivityManager
                    .getNetworkCapabilities(
                        connectivityManager.activeNetwork,
                    )
                    ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    ?: false

            trySend(if (isConnected) NetworkStatus.Available else NetworkStatus.Unavailable)

            connectivityManager.registerNetworkCallback(request, networkStatusCallback)

            awaitClose {
                Log.d(TAG, """NetworkCallback was closed, unregistering.""")
                connectivityManager.unregisterNetworkCallback(networkStatusCallback)
            }
        }
            .distinctUntilChanged()
            .shareIn(
                scope,
                // Continue running this callback for up to [STOP_TIMEOUT_MILLIS]
                // after the last subscriber to allow any new session started
                // before the deadline to re-use this listener.
                SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIMEOUT_MILLIS),
                replay = 1
            )
}

/**
 * Extension that removes nullability of getSystemService
 *
 * @param T The Type of the SystemService
 * @return A non null System service.
 * @throws [IllegalStateException] if the returned service is null.
 */
inline fun <reified T> Context.requireSystemService(): T {
    return checkNotNull(ContextCompat.getSystemService<T>(this, T::class.java)) {
        "A required System Service was null"
    }
}
