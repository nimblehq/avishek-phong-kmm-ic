package co.nimblehq.avishek.phong.kmmic.data.repository

import co.nimblehq.avishek.phong.kmmic.data.local.datasource.TokenLocalDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.body.LogInRequestBody
import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.TokenRemoteDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toToken
import co.nimblehq.avishek.phong.kmmic.domain.model.Token
import co.nimblehq.avishek.phong.kmmic.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthenticationRepositoryImpl(
    private val tokenRemoteDataSource: TokenRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource
): AuthenticationRepository {

    override fun hasCachedToken(): Boolean {
        return tokenLocalDataSource.getToken() != null
    }

    override fun logIn(email: String, password: String): Flow<Token> {
        return tokenRemoteDataSource
            .logIn(LogInRequestBody(email = email, password = password))
            .map { it.toToken() }
    }

    override fun save(token: Token) {
        tokenLocalDataSource.save(token.toApiModel())
    }
}
