package com.example.myapplication.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

class CoroutinesExecutor {

    suspend fun loadData(): String = withContext(Dispatchers.IO) {
        delay(1000)
        "Hello World"
    }
}

class UserRepository {

    private val data = mutableListOf<String>()

    suspend fun register(name: String) {
        delay(1000)
        data.add(name)
    }

    fun getAllUsers() = data.toList()
}

class Repository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val scope = CoroutineScope(dispatcher)

    fun initialize() {
        scope.launch {
            doSomethingHeavy()
        }
    }

    suspend fun fetchData(): String = withContext(dispatcher) {
        doSomethingHeavy()
        "Hello World"
    }

    private suspend fun doSomethingHeavy() {
        delay(5000)
    }
}

class SocketService(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val scope = CoroutineScope(dispatcher)

    fun shutDown() {
        scope.launch {
            delay(2000)
            println("AAA Completed!")
        }
    }
}

class CoroutinesTest {

    @Test
    fun testLoadData() = runTest {
        val executor = CoroutinesExecutor()
        val data = executor.loadData()
        assertEquals("Hello World", data)
    }

    @Test
    fun testLoadUsers() = runTest {
//        StandardTestDispatcher() is used in this case
        // queues up coroutines
        val repo = UserRepository()
        // Put launched coroutines into a scheduler
        launch {
            repo.register("Alice")
        }
        launch {
            repo.register("Bob")
        }
        // Run all tasks that are ready at current time
//        runCurrent()
        // Run pending things on scheduler that are scheduled by specified time
//        advanceTimeBy(1000)
        // Will run all tasks from the scheduler
        advanceUntilIdle()
        assertEquals(listOf("Alice", "Bob"), repo.getAllUsers())
    }

    @Test
    fun testLoadUsers2() = runTest(UnconfinedTestDispatcher()) {
        // UnconfinedTestDispatcher() Starts new coroutine eagerly
        // Can be used as main dispatcher
        // for coroutines that collect values
        val repo = UserRepository()
        // Put launched coroutines into a scheduler
        launch {
            repo.register("Alice")
        }
        launch {
            repo.register("Bob")
        }
        advanceUntilIdle()
        // each coroutine will be launched immediately, before launch method returns
        assertEquals(listOf("Alice", "Bob"), repo.getAllUsers())
    }

    @Test
    fun testRepository() = runTest {
        val repo = Repository(
            StandardTestDispatcher(
                testScheduler
            )
        )
        repo.initialize()
        advanceUntilIdle()
        val data = repo.fetchData()
        assertEquals("Hello World", data)
    }

    @Test
    fun uncompletedJobs() = runTest(timeout = 1.seconds) {
        launch(Dispatchers.IO) {
            // This will cause delay in test
            delay(500)
        }
    }

    @Test
    fun testSocketService() = runTest {
        val socketService = SocketService(
            StandardTestDispatcher(
                testScheduler
            )
        )
        socketService.shutDown()
    }
}
