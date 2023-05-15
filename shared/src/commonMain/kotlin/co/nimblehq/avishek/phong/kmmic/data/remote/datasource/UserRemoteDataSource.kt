package co.nimblehq.avishek.phong.kmmic.data.remote.datasource

import co.nimblehq.avishek.phong.kmmic.Endpoint
import co.nimblehq.avishek.phong.kmmic.data.remote.ApiClient
import co.nimblehq.avishek.phong.kmmic.data.remote.model.UserApiModel
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getProfile(): Flow<UserApiModel>
}

class UserRemoteDataSourceImpl(private val apiClient: ApiClient) : UserRemoteDataSource {

    override fun getProfile(): Flow<UserApiModel> {
        return apiClient.responseBody(Endpoint.USER_PROFILE, HttpMethod.Get)
    }
}
