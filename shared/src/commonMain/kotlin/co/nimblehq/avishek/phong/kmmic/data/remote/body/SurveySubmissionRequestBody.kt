package co.nimblehq.avishek.phong.kmmic.data.remote.body

import co.nimblehq.avishek.phong.kmmic.domain.model.SurveySubmission
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurveySubmissionApiBody(
    @SerialName("survey_id")
    val surveyId: String,
    @SerialName("questions")
    val questions: List<QuestionSubmissionApiBody>
) {
    constructor(surveySubmission: SurveySubmission) : this(
        surveySubmission.id,
        surveySubmission.questions.map(::QuestionSubmissionApiBody)
    )
}
