package co.nimblehq.avishek.phong.kmmic.presentation.module

import app.cash.turbine.test
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyQuestionViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var viewModel: SurveyQuestionViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        viewModel.clear()
    }

    @Test
    fun `when update view model state with a surveys, it return a list of QuestionUiModel`() = runTest {
        viewModel = SurveyQuestionViewModel()

        viewModel.updateStateWith(MockUtil.mockSurveyApiModel.toSurvey())

        viewModel.viewSate
            .test {
                val awaitItem = awaitItem()
                awaitItem.questions.first().questionTitle shouldBe "question_text"
                awaitItem.questions.first().answers.first().text shouldBe "answer_text"
            }
    }
}
