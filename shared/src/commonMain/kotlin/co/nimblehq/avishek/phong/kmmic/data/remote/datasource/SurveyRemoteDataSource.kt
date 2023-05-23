package co.nimblehq.avishek.phong.kmmic.data.remote.datasource

import co.nimblehq.avishek.phong.kmmic.Endpoint
import co.nimblehq.avishek.phong.kmmic.data.remote.ApiClient
import co.nimblehq.avishek.phong.kmmic.data.remote.body.SurveySubmissionApiBody
import co.nimblehq.avishek.phong.kmmic.data.remote.model.SurveyApiModel
import co.nimblehq.avishek.phong.kmmic.data.remote.param.GetSurveysQueryParam
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow

interface SurveyRemoteDataSource {
    fun getSurveys(params: GetSurveysQueryParam): Flow<List<SurveyApiModel>>
    fun getSurvey(id: String): Flow<SurveyApiModel>
    fun submitSurvey(body: SurveySubmissionApiBody): Flow<Unit>
}

class SurveyRemoteDataSourceImpl(private val apiClient: ApiClient) : SurveyRemoteDataSource {

    override fun getSurveys(params: GetSurveysQueryParam): Flow<List<SurveyApiModel>> {
        return apiClient.responseBodyWithParams(Endpoint.SURVEYS, HttpMethod.Get, params)
    }

    override fun getSurvey(id: String): Flow<SurveyApiModel> {
        return apiClient.responseBody(Endpoint.SURVEYS + "/$id", HttpMethod.Get)
    }

    override fun submitSurvey(body: SurveySubmissionApiBody): Flow<Unit> {
        println(body)
        return apiClient.emptyResponseBody(Endpoint.RESPONSES, HttpMethod.Post, body)
    }
}
