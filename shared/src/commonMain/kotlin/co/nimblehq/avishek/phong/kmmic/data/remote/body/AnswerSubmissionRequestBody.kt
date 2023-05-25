package co.nimblehq.avishek.phong.kmmic.data.remote.body

import co.nimblehq.avishek.phong.kmmic.domain.model.AnswerSubmission
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnswerSubmissionRequestBody(
    @SerialName("id")
    val id: String,
    @SerialName("answer")
    val answer: String?
) {
    constructor(answerSubmission: AnswerSubmission) : this(
        answerSubmission.id,
        answerSubmission.answer
    )
}
