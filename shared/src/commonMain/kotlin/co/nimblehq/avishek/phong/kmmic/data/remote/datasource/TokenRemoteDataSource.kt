package co.nimblehq.avishek.phong.kmmic.data.remote.datasource

import co.nimblehq.avishek.phong.kmmic.data.remote.ApiClient
import co.nimblehq.avishek.phong.kmmic.data.remote.body.RefreshTokenRequestBody
import co.nimblehq.avishek.phong.kmmic.data.remote.body.LogInRequestBody
import co.nimblehq.avishek.phong.kmmic.data.remote.constant.Endpoints
import co.nimblehq.avishek.phong.kmmic.data.remote.model.TokenApiModel
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

interface TokenRemoteDataSource {

    fun refreshToken(body: RefreshTokenRequestBody): Flow<TokenApiModel>
    fun logIn(body: LogInRequestBody): Flow<TokenApiModel>
}

class TokenRemoteDataSourceImpl(private val apiClient: ApiClient) : TokenRemoteDataSource {

    override fun refreshToken(body: RefreshTokenRequestBody): Flow<TokenApiModel> {
        return apiClient.responseBody(Endpoints.OAUTH_TOKEN, HttpMethod.Post, body)
    }

    override fun logIn(body: LogInRequestBody): Flow<TokenApiModel> {
        return apiClient.responseBody(Endpoints.OAUTH_TOKEN, HttpMethod.Post, body)
    }
}
