package co.nimblehq.avishek.phong.kmmic.presentation.uimodel

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey

data class SurveyUiModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val surveyQuestionUiModels: List<SurveyQuestionUiModel>
) {
    val largeImageUrl
        get() = imageUrl.plus("l")

    constructor(survey: Survey):
            this(
                survey.id,
                survey.title,
                survey.description,
                survey.coverImageUrl,
                // TODO: fix after the backend PR is merged
                listOf(
                    SurveyQuestionUiModel(
                        id = "1",
                        text = "How fulfilled did you feel during this WFH period?",
                        displayType = DisplayType.INTRO,
                        imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_l",
                        answerUiModels = List(5) {
                            AnswerUiModel(
                                id = it.toString(),
                                text = (it + 1).toString()
                            )
                        }
                    ),
                    SurveyQuestionUiModel(
                        id = "2",
                        text = "How fulfilled did you feel during this WFH period?",
                        displayType = DisplayType.DROPDOWN,
                        imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_l",
                        answerUiModels = List(5) {
                            AnswerUiModel(
                                id = it.toString(),
                                text = (it + 1).toString()
                            )
                        }
                    )
                )
            )
}
