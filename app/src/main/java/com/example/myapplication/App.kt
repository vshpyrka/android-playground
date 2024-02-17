package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.example.workmanager.WorkerRenameFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    val app = "Application"

    override fun onCreate() {
        super.onCreate()
        println("onCreate")
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setDefaultProcessName("com.example.myapplication.multiprocess")
            .setWorkerFactory(WorkerRenameFactory())
            .setMinimumLoggingLevel(Log.VERBOSE)
            .build()

}
