package co.nimblehq.avishek.phong.kmmic.presentation.uimodel

data class SurveyQuestionUiModel(
    val id: String,
    val text: String,
    val imageUrl: String,
    val displayType: DisplayType,
    val answerUiModels: List<AnswerUiModel>
)

enum class DisplayType {
    INTRO,
    DROPDOWN,
    UNKNOWN;

    companion object {
        fun from(value: String?): DisplayType =
            values().find { it.name.equals(value, true) } ?: UNKNOWN
    }
}
