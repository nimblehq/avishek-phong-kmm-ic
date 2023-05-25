package co.nimblehq.avishek.phong.kmmic.data.remote.body

import co.nimblehq.avishek.phong.kmmic.domain.model.SurveySubmission
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurveySubmissionRequestBody(
    @SerialName("survey_id")
    val surveyId: String,
    @SerialName("questions")
    val questions: List<QuestionSubmissionRequestBody>
) {
    constructor(surveySubmission: SurveySubmission) : this(
        surveySubmission.id,
        surveySubmission.questions.map(::QuestionSubmissionRequestBody)
    )
}
