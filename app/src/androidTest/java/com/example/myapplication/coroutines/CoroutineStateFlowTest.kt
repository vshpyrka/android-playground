package com.example.myapplication.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

interface MyRepository {
    fun scores(): Flow<Int>
}

class HotFakeRepository : MyRepository {
    private val flow = MutableSharedFlow<Int>()

    suspend fun emit(value: Int) {
        flow.emit(value)
    }

    override fun scores() = flow
}

class MyViewModel(private val repository: MyRepository) {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _data = MutableStateFlow(0)
    val data: StateFlow<Int> = _data.asStateFlow()

    val dataSharedIn: StateFlow<Int> = repository.scores()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000L), 0)

    fun initialize() {
        scope.launch {
            repository.scores().collect {
                _data.value = it
            }
        }
    }

}

class CoroutineStateFlowTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testHotFakeRepository() = runTest {
        val fakeRepository = HotFakeRepository()
        val viewModel = MyViewModel(fakeRepository)
        assertEquals(0, viewModel.data.value)

        viewModel.initialize()

        fakeRepository.emit(1)

        assertEquals(1, viewModel.data.value)

        fakeRepository.emit(2)
        fakeRepository.emit(3)
        assertEquals(3, viewModel.data.value)
    }

    @Test
    fun testHotFakeRepository2() = runTest {
        val fakeRepository = HotFakeRepository()
        val viewModel = MyViewModel(fakeRepository)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.dataSharedIn.collect()
        }
        assertEquals(0, viewModel.dataSharedIn.value)
        fakeRepository.emit(1)
        assertEquals(1, viewModel.dataSharedIn.value)
    }
}
