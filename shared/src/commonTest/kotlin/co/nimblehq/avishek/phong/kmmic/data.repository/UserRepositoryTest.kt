package co.nimblehq.avishek.phong.kmmic.data.repository

import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.UserRemoteDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.model.UserApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toUser
import co.nimblehq.avishek.phong.kmmic.domain.repository.UserRepository
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserRepositoryTest {

    @Mock
    private val mockUserRemoteDataSource = mock(classOf<UserRemoteDataSource>())
    private val mockThrowable = Throwable("mock")
    private val mockUser = UserApiModel(
        "id",
        "type",
        "phong.d@nimblehq.co",
        "Phong Vo",
        "https://www.example.com/image.png"
    )

    private lateinit var repository: UserRepository

    @BeforeTest
    fun setUp() {
        repository = UserRepositoryImpl(mockUserRemoteDataSource)
    }

    @Test
    fun `when getProfile returns success, it returns user`() = runTest {
        given(mockUserRemoteDataSource)
            .function(mockUserRemoteDataSource::getProfile)
            .whenInvoked()
            .thenReturn(
                flow {
                    emit(mockUser)
                }
            )

        repository.getProfile().collect {
            it shouldBe mockUser.toUser()
        }
    }

    @Test
    fun `when getProfile return failure, it returns error`() = runTest {
        given(mockUserRemoteDataSource)
            .function(mockUserRemoteDataSource::getProfile)
            .whenInvoked()
            .thenReturn(
                flow {
                    throw mockThrowable
                }
            )

        repository.getProfile().catch {
            it.message shouldBe mockThrowable.message
        }.collect()
    }
}
