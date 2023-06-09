package co.nimblehq.avishek.phong.kmmic.presentation.uimodel

import co.nimblehq.avishek.phong.kmmic.domain.model.Answer
import co.nimblehq.avishek.phong.kmmic.domain.model.AnswerInput

data class SurveyAnswerUiModel(
    val id: String,
    val text: String,
    val displayOrder: Int,
    val placeholder: String?,
) {
    constructor(answer: Answer) : this(
        id = answer.id,
        text = answer.content.toString(),
        displayOrder = answer.displayOrder,
        placeholder = answer.placeholder
    )
}

fun SurveyAnswerUiModel.toUserInput(): AnswerInput = AnswerInput(
    id = id,
    content = text
)
