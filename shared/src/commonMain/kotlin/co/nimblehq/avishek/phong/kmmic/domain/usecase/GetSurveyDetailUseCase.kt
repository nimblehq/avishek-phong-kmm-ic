package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow

interface GetSurveyDetailUseCase {
    operator fun invoke(id: String): Flow<Survey>
}

class GetSurveyDetailUseCaseImpl(
    private val surveyRepository: SurveyRepository
) : GetSurveyDetailUseCase {

    override operator fun invoke(id: String): Flow<Survey> {
        return surveyRepository.getSurvey(id)
    }
}
