package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.model.Token
import co.nimblehq.avishek.phong.kmmic.domain.usecase.LogInUseCase
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.newSingleThreadContext
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

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockThrowable = Throwable("mock")
    private val mockToken = Token("", "", 0, "", 0)

    @BeforeTest
    fun setUp() {
        viewModel = LogInViewModel(logInUseCase)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `When logInUseCase returns success, it updates view state with isSuccess as true`() = runTest {
        given(logInUseCase)
            .function(logInUseCase::invoke)
            .whenInvokedWith(any(), any())
            .thenReturn(flow { emit(mockToken) })

        viewModel.logIn("phong.d@nimblehq.co", "123456")

        viewModel.viewState.takeWhile { it.isSuccess }.collect {
            it.isSuccess shouldBe true
            it.isLoading shouldBe false
            it.isInvalidEmail shouldBe false
            it.isInvalidPassword shouldBe false
            it.error shouldBe null
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
