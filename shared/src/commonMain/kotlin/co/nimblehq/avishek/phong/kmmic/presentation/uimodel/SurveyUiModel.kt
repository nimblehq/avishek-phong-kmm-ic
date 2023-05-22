package co.nimblehq.avishek.phong.kmmic.presentation.uimodel

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey

data class SurveyUiModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String
) {
    val largeImageUrl
        get() = imageUrl.plus("l")

    constructor(survey: Survey):
            this(
                survey.id,
                survey.title,
                survey.description,
                survey.coverImageUrl
            )
}
