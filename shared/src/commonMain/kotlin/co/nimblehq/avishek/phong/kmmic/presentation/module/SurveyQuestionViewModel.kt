package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.model.Answer
import co.nimblehq.avishek.phong.kmmic.domain.model.Question
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType
import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.QuestionUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class SurveyQuestionViewState(
    val isLoading: Boolean,
    val errorMessage: String? = null,
    var questions: List<QuestionUiModel> = listOf(),
    val backgroundImageUrl: String = ""
) {
    constructor() : this(isLoading = false)
}

class SurveyQuestionViewModel : BaseViewModel() {

    private val _viewState: MutableStateFlow<SurveyQuestionViewState> =
        MutableStateFlow(SurveyQuestionViewState())
    val viewSate: StateFlow<SurveyQuestionViewState> = _viewState

    private var survey: Survey? = null

    fun updateStateWith(survey: Survey) {
        this.survey = survey
        val questionUiModels = survey.questions?.mapIndexed { index, question ->
                QuestionUiModel(question, "${index + 1}/${survey.questions.count()}")
            }

        _viewState.update {
            SurveyQuestionViewState(
                isLoading = it.isLoading,
                errorMessage = null,
                questions = questionUiModels.orEmpty(),
                backgroundImageUrl = survey.coverImageUrl.plus("l")
            )
        }
    }
}
