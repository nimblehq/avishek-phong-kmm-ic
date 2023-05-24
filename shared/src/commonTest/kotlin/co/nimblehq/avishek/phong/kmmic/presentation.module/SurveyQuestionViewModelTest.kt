package co.nimblehq.avishek.phong.kmmic.presentation.module

import app.cash.turbine.test
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import co.nimblehq.avishek.phong.kmmic.domain.usecase.SubmitSurveyAnswerUseCase
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.stopKoin
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyQuestionViewModelTest {

    @Mock
    private val useCase = mock(classOf<SubmitSurveyAnswerUseCase>())

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
    fun `when update view model state with a surveys, it returns a list of QuestionUiModel`() =
        runTest {
            viewModel = SurveyQuestionViewModel(useCase)

            viewModel.updateStateWith(MockUtil.mockSurveyApiModel.toSurvey())

            viewModel.viewSate
                .test {
                    val awaitItem = awaitItem()
                    awaitItem.questions.first().questionTitle shouldBe "question_text"
                    awaitItem.questions.first().answers.first().text shouldBe "answer_text"

                    cancelAndConsumeRemainingEvents()
                }
        }

    @Test
    fun `when submit answers successfully, it returns viewState's isSuccess true`() = runTest {
        given(useCase)
            .function(useCase::invoke)
            .whenInvokedWith(any())
            .thenReturn(flowOf(Unit))

        viewModel = SurveyQuestionViewModel(useCase)

        viewModel.updateStateWith(MockUtil.mockSurveyApiModel.toSurvey())
        viewModel.submitAnswer(MockUtil.mockQuestionUiModels)

        viewModel.viewSate
            .test {
                val onStartAwaitItem = awaitItem()
                onStartAwaitItem.isLoading shouldBe true
                onStartAwaitItem.isSuccess shouldBe false

                val onEachAwaitItem = awaitItem()
                onEachAwaitItem.isLoading shouldBe false
                onEachAwaitItem.isSuccess shouldBe true

                cancelAndConsumeRemainingEvents()
            }
    }
}
