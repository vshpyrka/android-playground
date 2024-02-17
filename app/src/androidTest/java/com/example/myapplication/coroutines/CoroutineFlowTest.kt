package com.example.myapplication.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

interface DataSource {
    fun counts(): Flow<Int>
}

class FlowRepository(private val dataSource: DataSource) {
    fun scores(): Flow<Int> = dataSource.counts().map {
        it * 10
    }
}

class ColdFakeDataSource : DataSource {
    override fun counts() = (1..10).asFlow()
}

class HotDataSource : DataSource {

    private val flow = MutableSharedFlow<Int>()

    suspend fun emit(value: Int) {
        flow.emit(value)
    }

    override fun counts(): Flow<Int> {
        return flow
    }
}

class CoroutineFlowTest {


    @Test
    fun useTerminalOperators() = runTest {
        val repository = FlowRepository(ColdFakeDataSource())
        val first = repository.scores().first()
        assertEquals(10, first)

        val list = repository.scores().toList()
        assertEquals(10, list[0])
        assertEquals(20, list[1])
        assertEquals(10, list.size)

        val list2 = repository.scores().take(2).toList()
        assertEquals(10, list2[0])
        assertEquals(20, list2[1])
        assertEquals(2, list2.size)
    }

    @Test
    fun continuouslyCollect() = runTest {
        val dataSource = HotDataSource()
        val repository = FlowRepository(dataSource)
        val values = mutableListOf<Int>()
//        val job = launch(UnconfinedTestDispatcher()) {
//            repository.scores().toList(values)
//        }

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            repository.scores().toList(values)
        }

        dataSource.emit(1)
        assertEquals(10, values[0])
//        job.cancel()

        dataSource.emit(2)
        dataSource.emit(3)
        assertEquals(30, values.last())
        assertEquals(3, values.size)
    }
}
