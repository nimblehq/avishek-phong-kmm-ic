package co.nimblehq.avishek.phong.kmmic.presentation.module

import app.cash.turbine.test
import co.nimblehq.avishek.phong.kmmic.domain.model.Token
import co.nimblehq.avishek.phong.kmmic.domain.usecase.LogInUseCase
import co.nimblehq.avishek.phong.kmmic.helper.TestDispatchersProvider
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LogInViewModelTest {

    @Mock
    val logInUseCase = mock(classOf<LogInUseCase>())

    private lateinit var viewModel: LogInViewModel

    private val mockToken = Token("", "", 0, "", 0)

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(TestDispatchersProvider.io)
        viewModel = LogInViewModel(logInUseCase, TestDispatchersProvider)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        TestDispatchersProvider.io.cancel()
        viewModel.clear()
    }

    @Test
    fun `When logInUseCase returns success, it updates view state with isSuccess as true`() = runTest {
        given(logInUseCase)
            .function(logInUseCase::invoke)
            .whenInvokedWith(any(), any())
            .thenReturn(flow { emit(mockToken) })

        viewModel.logIn("phong.d@nimblehq.co", "123456")

        viewModel.viewState.takeWhile { it.isSuccess }.test {
            val awaitItem = awaitItem()
            awaitItem.isSuccess shouldBe true
            awaitItem.isLoading shouldBe false
            awaitItem.isInvalidEmail shouldBe false
            awaitItem.isInvalidPassword shouldBe false
            awaitItem.error shouldBe null
        }
    }

    @Test
    fun `When logInUseCase returns failure, it updates view state with error message is mock`() = runTest {
        given(logInUseCase)
            .function(logInUseCase::invoke)
            .whenInvokedWith(any(), any())
            .thenReturn(flow { emit(mockToken) })

        viewModel.logIn("phong.d@nimblehq.co", "123456")

        viewModel.viewState.takeWhile { !it.error.isNullOrEmpty() }.collect {
            it.isSuccess shouldBe false
            it.isLoading shouldBe false
            it.isInvalidEmail shouldBe false
            it.isInvalidPassword shouldBe false
            it.error shouldBe "mock"
        }
    }

    @Test
    fun `When a user inputs invalid email, it updates view state with isInvalidEmail is true`() = runTest {
        given(logInUseCase)
            .function(logInUseCase::invoke)
            .whenInvokedWith(any(), any())
            .thenReturn(flow { emit(mockToken) })

        viewModel.logIn("123", "123456")

        viewModel.viewState.takeWhile { !it.error.isNullOrEmpty() }.collect {
            it.isSuccess shouldBe false
            it.isLoading shouldBe false
            it.isInvalidEmail shouldBe true
            it.isInvalidPassword shouldBe false
            it.error shouldBe null
        }
    }

    @Test
    fun `When a user didn't enter password, it updates view state with isInvalidPassword is true`() = runTest {
        given(logInUseCase)
            .function(logInUseCase::invoke)
            .whenInvokedWith(any(), any())
            .thenReturn(flow { emit(mockToken) })

        viewModel.logIn("123", "")

        viewModel.viewState.takeWhile { !it.error.isNullOrEmpty() }.collect {
            it.isSuccess shouldBe false
            it.isLoading shouldBe false
            it.isInvalidEmail shouldBe false
            it.isInvalidPassword shouldBe true
            it.error shouldBe null
        }
    }
}
