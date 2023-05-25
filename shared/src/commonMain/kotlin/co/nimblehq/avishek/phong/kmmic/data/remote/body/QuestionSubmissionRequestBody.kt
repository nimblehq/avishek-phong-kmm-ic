package co.nimblehq.avishek.phong.kmmic.data.remote.body

import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionSubmission
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionSubmissionRequestBody(
    @SerialName("id")
    val id: String,
    @SerialName("answers")
    val answers: List<AnswerSubmissionRequestBody>
) {
    constructor(questionSubmission: QuestionSubmission) : this(
        questionSubmission.id,
        questionSubmission.answers.map(::AnswerSubmissionRequestBody)
    )
}
