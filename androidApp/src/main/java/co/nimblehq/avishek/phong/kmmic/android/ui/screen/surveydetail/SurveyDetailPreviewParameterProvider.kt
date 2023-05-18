package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType.*
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.*

val answerUiModels = List(5) {
    SurveyAnswerUiModel(
        id = (it + 1).toString(),
        text = "Text ${it + 1}",
        displayOrder = it,
        placeholder = "Your thoughts"
    )
}

@Suppress("MagicNumber")
class SurveyDetailScreenPreviewParameterProvider :
    PreviewParameterProvider<SurveyDetailScreenPreviewParameterProvider.Params> {
    override val values = sequenceOf(
        Params(
            isLoading = false,
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                questionUiModels = listOf(
                    QuestionUiModel(
                        id = "1",
                        step = "1/1",
                        displayOrder = 1,
                        questionTitle = "How fulfilled did you feel during this WFH period?",
                        displayType = DROPDOWN,
                        answers = answerUiModels,
                        userInputs = emptySet()
                    )
                )
            )
        ),
        Params(
            isLoading = false,
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                questionUiModels = listOf(
                    QuestionUiModel(
                        id = "1",
                        step = "1/1",
                        displayOrder = 1,
                        questionTitle = "How fulfilled did you feel during this WFH period?",
                        displayType = STAR,
                        answers = answerUiModels,
                        userInputs = emptySet()
                    )
                )
            )
        ),
        Params(
            isLoading = false,
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                questionUiModels = listOf(
                    QuestionUiModel(
                        id = "1",
                        step = "1/1",
                        displayOrder = 1,
                        questionTitle = "How fulfilled did you feel during this WFH period?",
                        displayType = HEART,
                        answers = answerUiModels,
                        userInputs = emptySet()
                    )
                )
            )
        ),
        Params(
            isLoading = false,
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                questionUiModels = listOf(
                    QuestionUiModel(
                        id = "1",
                        step = "1/1",
                        displayOrder = 1,
                        questionTitle = "How fulfilled did you feel during this WFH period?",
                        displayType = SMILEY,
                        answers = answerUiModels,
                        userInputs = emptySet()
                    )
                )
            )
        ),
        Params(
            isLoading = false,
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                questionUiModels = listOf(
                    QuestionUiModel(
                        id = "1",
                        step = "1/1",
                        displayOrder = 1,
                        questionTitle = "How fulfilled did you feel during this WFH period?",
                        displayType = TEXTAREA,
                        answers = answerUiModels,
                        userInputs = emptySet()
                    )
                )
            )
        ),
        Params(
            isLoading = false,
            survey = SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                imageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
                questionUiModels = listOf(
                    QuestionUiModel(
                        id = "1",
                        step = "1/1",
                        displayOrder = 1,
                        questionTitle = "How fulfilled did you feel during this WFH period?",
                        displayType = TEXTFIELD,
                        answers = List(5) {
                            SurveyAnswerUiModel(
                                id = (it + 1).toString(),
                                text = "Text ${it + 1}",
                                displayOrder = it,
                                placeholder = "Your thoughts"
                            )
                        },
                        userInputs = emptySet()
                    )
                )
            )
        )
    )

    class Params(
        val isLoading: Boolean,
        val survey: SurveyUiModel,
    )
}
