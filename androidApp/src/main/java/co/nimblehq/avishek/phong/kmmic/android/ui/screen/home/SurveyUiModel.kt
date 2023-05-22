package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

data class SurveyUiModel(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val questions: List<QuestionUiModel> = emptyList()
)
