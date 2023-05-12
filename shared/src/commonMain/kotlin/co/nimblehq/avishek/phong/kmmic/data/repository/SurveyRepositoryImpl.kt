package co.nimblehq.avishek.phong.kmmic.data.repository

import co.nimblehq.avishek.phong.kmmic.data.local.datasource.SurveyLocalDataSource
import co.nimblehq.avishek.phong.kmmic.data.local.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.SurveyRemoteDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toSurveyRealmObject
import co.nimblehq.avishek.phong.kmmic.data.remote.param.GetSurveysQueryParam
import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last

private const val FIRST_PAGE_NUMBER = 1

class SurveyRepositoryImpl(
    private val surveyRemoteDataSource: SurveyRemoteDataSource,
    private val surveyLocalDataSource: SurveyLocalDataSource
) : SurveyRepository {

    override fun getSurveys(
        pageNumber: Int,
        pageSize: Int,
        isForceLatestData: Boolean
    ): Flow<List<Survey>> {
        return flow {
            if (isForceLatestData) {
                surveyLocalDataSource.removeAllSurveys()
            } else if (pageNumber == FIRST_PAGE_NUMBER) {
                val localSurveys = surveyLocalDataSource.getSurveys().map { it.toSurvey() }
                if (localSurveys.isNotEmpty()) {
                    emit(localSurveys)
                }
            }

            val apiSurveys = surveyRemoteDataSource
                .getSurveys(GetSurveysQueryParam(pageNumber, pageSize))
                .last()

            val latestSurveys = apiSurveys.map { it.toSurvey() }

            emit(latestSurveys)

            surveyLocalDataSource.saveSurveys(apiSurveys.map { it.toSurveyRealmObject() })
        }
    }
}
