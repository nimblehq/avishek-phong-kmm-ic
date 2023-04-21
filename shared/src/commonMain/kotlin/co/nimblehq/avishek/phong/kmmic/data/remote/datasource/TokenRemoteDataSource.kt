package co.nimblehq.avishek.phong.kmmic.data.remote.datasource

import co.nimblehq.avishek.phong.kmmic.data.remote.ApiClient
import co.nimblehq.avishek.phong.kmmic.data.remote.body.RefreshTokenApiBody
import co.nimblehq.avishek.phong.kmmic.data.remote.model.TokenApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.path
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

interface TokenRemoteDataSource {

    fun refreshToken(body: RefreshTokenApiBody): Flow<TokenApiModel>
}

class TokenRemoteDataSourceImpl(private val apiClient: ApiClient) : TokenRemoteDataSource {

    override fun refreshToken(body: RefreshTokenApiBody): Flow<TokenApiModel> {
        return apiClient.responseBody(
            HttpRequestBuilder().apply {
                path("/v1/oauth/token")
                method = HttpMethod.Post
                setBody(body)
            }
        )
    }
}
