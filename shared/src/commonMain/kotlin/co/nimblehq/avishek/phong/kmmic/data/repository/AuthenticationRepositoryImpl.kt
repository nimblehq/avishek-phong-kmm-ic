package co.nimblehq.avishek.phong.kmmic.data.repository

import co.nimblehq.avishek.phong.kmmic.data.local.datasource.TokenLocalDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.TokenRemoteDataSource
import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val tokenRemoteDataSource: TokenRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource
): AuthenticationRepository {

    override fun hasCachedToken(): Boolean {
        return tokenLocalDataSource.getToken() != null
    }
}
