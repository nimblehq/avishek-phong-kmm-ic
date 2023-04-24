package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.data.remote.model.TokenApiModel
import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository
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
class CheckLoggedInUseCaseTest {

    @Mock
    private val mockRepository = mock(classOf<AuthenticationRepository>())

    private lateinit var useCase: CheckLoggedInUseCase

    @BeforeTest
    fun setUp() {
        useCase = CheckLoggedInUseCaseImpl(mockRepository)
    }

    @Test
    fun `when hasCachedToken is called and user logged in before - it returns true`() = runTest {
        given(mockRepository)
            .function(mockRepository::hasCachedToken)
            .whenInvoked()
            .thenReturn(true)

        useCase shouldBe true
    }

    @Test
    fun `when hasCachedToken is called and user didn't log in before - it returns false`() = runTest {
        given(mockRepository)
            .function(mockRepository::hasCachedToken)
            .whenInvoked()
            .thenReturn(false)

        useCase shouldBe false
    }
}
