package co.nimblehq.avishek.phong.kmmic.presentation.uimodel

import co.nimblehq.avishek.phong.kmmic.domain.model.*
import co.nimblehq.avishek.phong.kmmic.data.remote.model.*

data class QuestionUiModel(
    val id: String,
    val step: String,
    val displayOrder: Int,
    val displayType: QuestionDisplayType,
    val questionTitle: String,
    val answers: List<SurveyAnswerUiModel>,
    var userInputs: Set<AnswerInput>

) {
    constructor(question: Question, step: String):  this(
        id = question.id,
        step = step,
        displayOrder = question.displayOrder,
        displayType = question.displayType,
        questionTitle = question.text,
        answers = question.sortedAnswers.map { SurveyAnswerUiModel(it) },
        userInputs = emptySet()
    )
}

fun Question.toQuestionUiModel(index: Int, totalStep: Int): QuestionUiModel = QuestionUiModel(
    question = this,
    step = "${index + 1}/${totalStep}"
)

fun List<QuestionUiModel>.mapToQuestionSubmissions(): List<QuestionSubmission> {
    return this.map { question ->
        QuestionSubmission(
            id = question.id,
            answers = question.answers.mapToAnswerSubmissions()
        )
    }
}

fun List<QuestionUiModel>.toSurveySubmission(surveyId: String): SurveySubmission = SurveySubmission(
    id = surveyId,
    questions = this.mapToQuestionSubmissions()
)
