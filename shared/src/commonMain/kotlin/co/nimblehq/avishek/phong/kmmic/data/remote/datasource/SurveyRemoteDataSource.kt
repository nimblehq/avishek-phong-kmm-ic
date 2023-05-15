package co.nimblehq.avishek.phong.kmmic.data.remote.datasource

import co.nimblehq.avishek.phong.kmmic.Endpoint
import co.nimblehq.avishek.phong.kmmic.data.remote.ApiClient
import co.nimblehq.avishek.phong.kmmic.data.remote.model.SurveyApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.param.GetSurveysQueryParam
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow

interface SurveyRemoteDataSource {
    fun getSurveys(params: GetSurveysQueryParam): Flow<List<SurveyApiModel>>
}

class SurveyRemoteDataSourceImpl(private val apiClient: ApiClient) : SurveyRemoteDataSource {

    override fun getSurveys(params: GetSurveysQueryParam): Flow<List<SurveyApiModel>> {
        return apiClient.responseBodyWithParams(Endpoint.SURVEYS, HttpMethod.Get, params)
    }
}
