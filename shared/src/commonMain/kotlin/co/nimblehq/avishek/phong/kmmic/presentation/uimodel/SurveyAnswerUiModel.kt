package co.nimblehq.avishek.phong.kmmic.presentation.uimodel

import co.nimblehq.avishek.phong.kmmic.domain.model.Answer
import co.nimblehq.avishek.phong.kmmic.domain.model.AnswerSubmission

data class SurveyAnswerUiModel(
    val id: String,
    val text: String?,
    val displayOrder: Int,
    val placeholder: String?
) {
    constructor(answer: Answer): this(
        id = answer.id,
        text = answer.content,
        displayOrder = answer.displayOrder,
        placeholder = answer.placeholder
    )
}

fun SurveyAnswerUiModel.toAnswerSubmission() = AnswerSubmission(id, text)

fun List<SurveyAnswerUiModel>.mapToAnswerSubmissions(): List<AnswerSubmission> {
    return this.map { it.toAnswerSubmission() }
}
