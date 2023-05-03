package co.nimblehq.avishek.phong.kmmic.data.remote.datasource

import co.nimblehq.avishek.phong.kmmic.data.remote.ApiClient
import co.nimblehq.avishek.phong.kmmic.data.remote.body.RefreshTokenRequestBody
import co.nimblehq.avishek.phong.kmmic.data.remote.model.TokenApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.path
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

interface TokenRemoteDataSource {

    fun refreshToken(body: RefreshTokenRequestBody): Flow<TokenApiModel>
}

class TokenRemoteDataSourceImpl(private val apiClient: ApiClient) : TokenRemoteDataSource {

    override fun refreshToken(body: RefreshTokenRequestBody): Flow<TokenApiModel> {
        return apiClient.responseBody("/v1/oauth/token", HttpMethod.Post, body)
    }
}
