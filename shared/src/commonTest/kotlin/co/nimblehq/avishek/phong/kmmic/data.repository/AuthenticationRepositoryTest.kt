package co.nimblehq.avishek.phong.kmmic.data.repository

import co.nimblehq.avishek.phong.kmmic.data.local.datasource.TokenLocalDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.TokenRemoteDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.model.TokenApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toToken
import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class AuthenticationRepositoryTest {

    @Mock
    private val mockTokenLocalDataSource = mock(classOf<TokenLocalDataSource>())
    @Mock
    private val mockTokenRemoteDataSource = mock(classOf<TokenRemoteDataSource>())

    private lateinit var repository: AuthenticationRepository

    private val mockToken = TokenApiModel(
        "id",
        "type",
        "accessToken",
        "tokenType",
        0,
        "refreshToken",
        0
    )
    private val mockThrowable = Throwable("mock")

    @BeforeTest
    fun setUp() {
        repository = AuthenticationRepositoryImpl(mockTokenRemoteDataSource, mockTokenLocalDataSource)
    }

    @Test
    fun `when hasCachedToken is called and user logged in before - it returns true`() = runTest {
        given(mockTokenLocalDataSource)
            .function(mockTokenLocalDataSource::getToken)
            .whenInvoked()
            .thenReturn(mockToken)

        repository.hasCachedToken() shouldBe true
    }

    @Test
    fun `when hasCachedToken is called and user didn't log in before - it returns false`() = runTest {
        given(mockTokenLocalDataSource)
            .function(mockTokenLocalDataSource::getToken)
            .whenInvoked()
            .thenReturn(null)

        repository.hasCachedToken() shouldBe false
    }

    @Test
    fun `when logIn is called - the data source returns token`() = runTest {
        given(mockTokenRemoteDataSource)
            .function(mockTokenRemoteDataSource::logIn)
            .whenInvokedWith(any())
            .thenReturn(
                flow { emit(mockToken) }
            )

        repository.logIn("email", "password").collect {
            it shouldBe  mockToken.toToken()
        }
    }

    @Test
    fun `when login is called - the data source returns error`() = runTest {
        given(mockTokenRemoteDataSource)
            .function(mockTokenRemoteDataSource::logIn)
            .whenInvokedWith(any())
            .thenReturn(
                flow {
                    throw mockThrowable
                }
            )

        repository.logIn("email", "password")
            .catch {
                it.message shouldBe mockThrowable.message
            }
            .collect()
    }
}
