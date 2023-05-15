package co.nimblehq.avishek.phong.kmmic.domain.repository

import co.nimblehq.avishek.phong.kmmic.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    fun hasCachedToken(): Boolean
    fun logIn(email: String, password: String): Flow<Token>
    fun save(token: Token)
}
