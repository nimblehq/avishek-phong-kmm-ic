package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.usecase.CheckLoggedInUseCase
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @Mock
    val checkLoggedInUseCase = mock(classOf<CheckLoggedInUseCase>())

    private lateinit var viewModel: SplashViewModel

    @BeforeTest
    fun setUp() {
        viewModel = SplashViewModel(checkLoggedInUseCase)
    }

    @Test
    fun `when checkLoggedInUseCase is invoked and user logged in before - it return true`() = runTest {
        given(checkLoggedInUseCase)
            .function(checkLoggedInUseCase::invoke)
            .whenInvoked()
            .thenReturn(true)

        viewModel.checkIfUserLoggedIn() shouldBe true
    }

    @Test
    fun `when checkLoggedInUseCase is invoked and user didn't log in before - it return false`() = runTest {
        given(checkLoggedInUseCase)
            .function(checkLoggedInUseCase::invoke)
            .whenInvoked()
            .thenReturn(false)

        viewModel.checkIfUserLoggedIn() shouldBe false
    }
}
