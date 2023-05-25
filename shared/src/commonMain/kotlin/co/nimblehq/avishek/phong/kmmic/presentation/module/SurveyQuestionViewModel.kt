package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.usecase.SubmitSurveyAnswerUseCase
import co.nimblehq.avishek.phong.kmmic.helper.DispatchersProvider
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.*
import kotlinx.coroutines.flow.*

data class SurveyQuestionViewState(
    val isLoading: Boolean,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    var questions: List<QuestionUiModel> = listOf(),
    val backgroundImageUrl: String = "",
) {
    constructor() : this(isLoading = false)
}

class SurveyQuestionViewModel(
    private val submitSurveyAnswerUseCase: SubmitSurveyAnswerUseCase,
    private val dispatchersProvider: DispatchersProvider,
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<SurveyQuestionViewState> =
        MutableStateFlow(SurveyQuestionViewState())
    val viewSate: StateFlow<SurveyQuestionViewState> = _viewState

    private var survey: Survey? = null

    fun updateStateWith(survey: Survey) {
        this.survey = survey
        val questionUiModels = survey.questions?.mapIndexed { index, question ->
            question.toQuestionUiModel(index = index, totalStep = survey.questions.count())
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

    fun submitAnswer(questions: List<QuestionUiModel>) {
        val surveyId = survey?.id ?: return
        val surveySubmission = questions.toSurveySubmission(surveyId)
        submitSurveyAnswerUseCase(surveySubmission)
            .flowOn(dispatchersProvider.io)
            .onStart { setStateLoading() }
            .catch { handleError(it) }
            .onEach { handleSubmitSuccess() }
            .launchIn(vmScope)
    }

    private fun setStateLoading() {
        _viewState.update {
            SurveyQuestionViewState(
                isLoading = true,
                isSuccess = false,
                errorMessage = null,
                questions = it.questions,
                backgroundImageUrl = it.backgroundImageUrl
            )
        }
    }

    private fun handleError(error: Throwable) {
        _viewState.update {
            SurveyQuestionViewState(
                isLoading = false,
                isSuccess = false,
                errorMessage = error.message,
                questions = it.questions,
                backgroundImageUrl = it.backgroundImageUrl
            )
        }
    }

    private fun handleSubmitSuccess() {
        _viewState.update {
            SurveyQuestionViewState(
                isLoading = false,
                isSuccess = true,
                errorMessage = null,
                questions = it.questions,
                backgroundImageUrl = it.backgroundImageUrl
            )
        }
    }
}
