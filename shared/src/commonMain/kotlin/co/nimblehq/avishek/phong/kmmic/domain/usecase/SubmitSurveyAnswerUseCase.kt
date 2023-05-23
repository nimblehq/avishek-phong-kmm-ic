package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.SurveySubmission
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow

interface SubmitSurveyAnswerUseCase {
    operator fun invoke(submission: SurveySubmission): Flow<Unit>
}

class SubmitSurveyAnswerUseCaseImpl(
    private val repository: SurveyRepository
) : SubmitSurveyAnswerUseCase {

    override operator fun invoke(submission: SurveySubmission): Flow<Unit> {
        return repository.submitSurvey(submission)
    }
}
