package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.repository.UserRepository
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetProfileUseCaseTest {

    @Mock
    private val mockUserRepository = mock(classOf<UserRepository>())

    private lateinit var useCase: GetUserProfileUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetUserProfileUseCaseImpl(mockUserRepository)
    }

    @Test
    fun `when getProfile is called successfully, it returns user`() = runTest {
        given(mockUserRepository)
            .function(mockUserRepository::getProfile)
            .whenInvoked()
            .thenReturn(flowOf(MockUtil.mockUser))

        useCase().collect {
            it shouldBe MockUtil.mockUser
        }
    }

    @Test
    fun `when getProfile returns a failure, it returns an error`() = runTest {
        given(mockUserRepository)
            .function(mockUserRepository::getProfile)
            .whenInvoked()
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )


        useCase()
            .catch {
                it.message shouldBe MockUtil.mockThrowable.message
            }
            .collect()
    }
}
