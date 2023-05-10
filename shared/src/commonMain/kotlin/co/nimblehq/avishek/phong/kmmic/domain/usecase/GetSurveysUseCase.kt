package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow

interface GetSurveysUseCase {
    operator fun invoke(pageNumber: Int, pageSize: Int, isForceLatestData: Boolean): Flow<List<Survey>>
}

class GetSurveysUseCaseImpl(
    private val surveyRepository: SurveyRepository
) : GetSurveysUseCase {

    override operator fun invoke(
        pageNumber: Int,
        pageSize: Int,
        isForceLatestData: Boolean
    ): Flow<List<Survey>> {
        return surveyRepository.getSurveys(pageNumber, pageSize, isForceLatestData)
    }
}
