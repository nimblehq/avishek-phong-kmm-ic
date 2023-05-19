package co.nimblehq.avishek.phong.kmmic.domain.repository

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import kotlinx.coroutines.flow.Flow

interface SurveyRepository {
    fun getSurveys(pageNumber: Int, pageSize: Int, isForceLatestData: Boolean): Flow<List<Survey>>
    fun getSurvey(id: String): Flow<Survey>
}
