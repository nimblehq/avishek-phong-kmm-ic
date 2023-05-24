package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.model.Answer
import co.nimblehq.avishek.phong.kmmic.domain.model.AnswerSubmission
import co.nimblehq.avishek.phong.kmmic.domain.model.Question
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionSubmission
import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.model.SurveySubmission
import co.nimblehq.avishek.phong.kmmic.domain.usecase.SubmitSurveyAnswerUseCase
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.QuestionUiModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.toQuestionUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

data class SurveyQuestionViewState(
    val isLoading: Boolean,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    var questions: List<QuestionUiModel> = listOf(),
    val backgroundImageUrl: String = ""
) {
    constructor() : this(isLoading = false)
}

class SurveyQuestionViewModel(
    private val submitSurveyAnswerUseCase: SubmitSurveyAnswerUseCase
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
        val surveySubmission = SurveySubmission(
            id = surveyId,
            questions = questions.map { question ->
                QuestionSubmission(
                    id = question.id,
                    answers = question.answers.map { answer ->
                        AnswerSubmission(answer.id, answer.text)
                    }
                )
            }
        )
        submitSurveyAnswerUseCase(surveySubmission)
            .onStart { setStateLoading() }
            .catch { handleError(it) }
            .onEach { handleSubmitSuccess() }
            .launchIn(viewModelScope)
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
