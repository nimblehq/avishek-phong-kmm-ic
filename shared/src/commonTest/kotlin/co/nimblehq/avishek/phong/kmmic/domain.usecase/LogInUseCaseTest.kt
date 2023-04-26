package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.Token
import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.collect
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class LogInUseCaseTest {

    @Mock
    private val mockRepository = mock(classOf<AuthenticationRepository>())

    private lateinit var useCase: LogInUseCase

    private val mockThrowable = Throwable("mock")
    private val mockToken = Token(
        "",
        "",
        0,
        "",
        0
    )

    @BeforeTest
    fun setUp() {
        useCase = LogInUseCaseImpl(mockRepository)
    }
    @Test
    fun `when logIn is called - the repository saves and returns token`() = runTest {
        given(mockRepository)
            .function(mockRepository::logIn)
            .whenInvokedWith(any(), any())
            .thenReturn(
                flow {
                    emit(mockToken)
                }
            )

        useCase("email", "password").collect {
            it shouldBe mockToken
        }

        verify(mockRepository)
            .function(mockRepository::save)
            .with(any())
            .wasInvoked(exactly = 1.time)
    }

    @Test
    fun `when login is called - the repository returns error`() = runTest {
        given(mockRepository)
            .function(mockRepository::logIn)
            .whenInvokedWith(any(), any())
            .thenReturn(
                flow {
                    throw mockThrowable
                }
            )

        useCase("email", "password")
            .catch {
                it.message shouldBe mockThrowable.message
            }
            .collect()
    }
}
