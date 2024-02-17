package com.example.myapplication

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.TreeMap
import java.util.TreeSet
import java.util.function.Function
import kotlin.system.measureTimeMillis


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val treeSet = TreeSet<String>()
        val treeMap = TreeMap<String, String>()
        val hashMap = HashMap<String, String>()
        val hasSet = HashSet<String>()




        // Currying


        val triadder =
            Function { u: Int ->
                Function { w: Int ->
                    Function { v: Int ->
                        u + w + v
                    }
                }
            }

        val result = triadder
            .apply(2)
            .apply(3)
            .apply(4)
    }

    @Test
    fun `Your first coroutine`() {
        GlobalScope.launch {
            delay(1000)
            println("World " + Thread.currentThread().name)
        }
        println("Hello")
        Thread.sleep(2000)
    }

    @Test
    fun `Bridging blocking and non-blocking worlds`() {
        GlobalScope.launch {
            println("Smth")
            delay(1000)
            println("World")
        }
        println("Hello")
        runBlocking {
            println("Before delay")
            delay(2000)
            println("After delay")
        }
    }

    @Test
    fun `Waiting for a job`() = runBlocking {
        val job = GlobalScope.launch {
            delay(1000)
            println("World")
        }
        println("Hello")
        job.join()
    }

    @Test
    fun `Structured concurrency`() = runBlocking {
        launch {
            delay(1000)
            println("World")
        }
        println("Hello")
    }

    @Test
    fun `Scope builder`() = runBlocking {
        launch {
            delay(200)
            println("Task from runBlocking")
        }

        coroutineScope {
            launch {
                delay(500)
                println("Task from nested launch")
            }

            delay(100)
            println("Task from coroutine scope")
        }

        println("Coroutine scope is over")
    }

    @Test
    fun `Extract function refactoring`() = runBlocking {
        launch {
            doWorld()
        }
        println("Hello")
    }

    private suspend fun doWorld() {
        delay(1000)
        println("World")
    }

    @Test
    fun `Coroutines ARE light-weight`() = runBlocking {
        repeat(100_000) {
            launch {
                delay(1000)
                println(". = ${System.currentTimeMillis()}")
            }
        }
    }

    @Test
    fun `Cancelling coroutine execution`() = runBlocking {
        val job = launch {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500)
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
//        job.cancel()
//        job.join() // affects isComplete state
        job.cancelAndJoin()
        println("main: Now I can quit. ${job.isCancelled} ${job.isCompleted}")
    }

    @Test
    fun `Cancellation is cooperative`() = runBlocking {
        // Continues background work even when cancel is invoked
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            printThread("In Launch")
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) { // computation loop, just wastes CPU
                // print a message twice a second
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        printThread("Before delay")
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        printThread("After delay")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }

    @Test
    fun `Making computation code cancellable`() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }

    @Test
    fun `Closing resources with finally`() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                println("job: I'm running finally")
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }

    @Test
    fun `Run non-cancellable block`() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }

    @Test
    fun `Timeout coroutine`() = runBlocking {
        launch {
            withTimeout(1300) {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            }
        }
        println("When complete")
    }

    @Test
    fun `Sequential by default`() = runBlocking {
        launch {
            val time = measureTimeMillis {
                val one = doSomethingUsefulOne()
                val two = doSomethingUsefulTwo()
                println("The answer is ${one + two}")
            }
            println("Completed in $time ms")
        }
        delay(3000)
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        return 29
    }

    @Test
    fun `Concurrent using async`() = runBlocking {
        launch {
            val time = measureTimeMillis {
                val one = async { doSomethingUsefulOne() }
                val two = async { doSomethingUsefulTwo() }

                val oneResult = one.await()
                val twoResult = two.await()
                println("The answer is ${oneResult + twoResult} ")
            }

            println("Completed in $time ms")
        }
        println("Start work")
        delay(2000)
    }

    @Test
    fun `Lazily started async`() = runBlocking {
        launch {
            val time = measureTimeMillis {
                val one = async(start = CoroutineStart.LAZY) {
                    doSomethingUsefulOne()
                }
                val two = async(start = CoroutineStart.LAZY) {
                    doSomethingUsefulTwo()
                }

                one.start() // start the first one
                two.start() // start the second one
                println("The answer is ${one.await() + two.await()}")
            }
            println("Completed in $time ms")
        }
        println("Start work")
    }

    // The result type of somethingUsefulOneAsync is Deferred<Int>
    private fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    // The result type of somethingUsefulTwoAsync is Deferred<Int>
    private fun somethingUsefulTwoAsync() = GlobalScope.async {
        doSomethingUsefulTwo()
    }

    @Test
    fun `Async-style functions`() {
        val time = measureTimeMillis {
            val one = somethingUsefulOneAsync()
            val two = somethingUsefulTwoAsync()
            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }
        println("Completed in $time ms")
    }

    @Test
    fun `Structured concurrency with async`() = runBlocking {
        launch {
            val time = measureTimeMillis {
                println("The answer is ${concurrentSum()}")
            }
            println("Completed in $time ms")
        }
        printThread()
    }

    private suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        one.await() + two.await()
    }

    @Test
    fun `Cancellation is always propagated through coroutines hierarchy`() = runBlocking {
        try {
            failedConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
        printThread()
    }

    private suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async<Int> {
            try {
                delay(2000)
                println("Everything seems to be fine")
                42
            } catch (e: Exception) {
                println("First child was cancelled")
                return@async 42
            } finally {
                println("First child Complete")
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }

    private fun printThread(tag: String = "") {
        println("$tag Thread ID = ${Thread.currentThread().id} ${Thread.currentThread().name}")
    }
}
