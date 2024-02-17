package com.example.myapplication.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.myapplication.R
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        println("AAA onCreate")

// The work is still happening even if the view is inactive
// View will receive latest updates after becoming started
// Collect is suspended until view becomes started again
//
//        lifecycleScope.launchWhenStarted {
//            getCountdownTime().collect {
//                println("AAA $it")
//            }
//        }

// The same way of declaring previous example
//
//        lifecycleScope.launch {
//            whenStarted {
//                getCountdownTime().collect {
//                    println("AAA $it")
//                }
//            }
//        }
//
// Flow still emits data and keeps sending new data to the ui
// UI keeps observing data even in inactive state
//
//        lifecycleScope.launch {
//            getCountdownTime()
//                .onEach {
//                    println("AAA $it")
//                }.launchIn(lifecycleScope)
//        }

// Flow is automatically stopped when ui is in stopped state
// Flow is restarted when ui becomes started again
//
//        lifecycleScope.launch {
//            getCountdownTime().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
//                .collect {
//                    println("AAA $it")
//                }
//        }

        // The same way of declaring previous example
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                getCountdownTime().collect {
                    println("AAA $it")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("AAA onStart")
    }

    override fun onResume() {
        super.onResume()
        println("AAA onResume")
    }

    override fun onPause() {
        super.onPause()
        println("AAA onPause")
    }

    override fun onStop() {
        super.onStop()
        println("AAA onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("AAA onDestroy")
    }

    private suspend fun launchTick() = flow {
        for (i in 0 until 100) {
            emit(i.toString())
            println("AAA wait")
            delay(1000)
        }
    }

    private fun getCountdownTime(): Flow<String> = callbackFlow {
        val timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                println("AAA onTick $millisUntilFinished")
                trySend(millisUntilFinished.toString())
            }

            override fun onFinish() {
                println("AAA onFinish")
            }
        }
        timer.start()

        awaitClose {
            println("AAA Closed")
            timer.cancel()
        }
    }
}
