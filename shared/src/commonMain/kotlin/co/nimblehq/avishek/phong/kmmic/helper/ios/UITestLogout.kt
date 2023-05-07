package co.nimblehq.avishek.phong.kmmic.helper.ios

import co.nimblehq.avishek.phong.kmmic.data.local.datasource.TokenLocalDataSource
import io.ktor.util.logging.KtorSimpleLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UITestLogout: KoinComponent {

    private val localDataSource: TokenLocalDataSource by inject()

    fun removeAccessToken() {
        try {
            localDataSource.removeToken()
        } catch (e: Exception) {
            KtorSimpleLogger(e.message ?: "An error occurred while removing access token")
        }
    }
}
