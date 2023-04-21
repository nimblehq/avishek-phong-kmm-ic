package co.nimblehq.avishek.phong.kmmic.domain.repository

interface AuthenticationRepository {

    fun hasCachedToken(): Boolean
}
