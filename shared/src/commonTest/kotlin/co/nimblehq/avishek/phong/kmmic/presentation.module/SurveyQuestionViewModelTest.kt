package co.nimblehq.avishek.phong.kmmic.presentation.module

import app.cash.turbine.test
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.domain.usecase.SubmitSurveyAnswerUseCase
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import co.nimblehq.avishek.phong.kmmic.helper.TestDispatchersProvider
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyQuestionViewModelTest {

    @Mock
    private val useCase = mock(classOf<SubmitSurveyAnswerUseCase>())

    private lateinit var viewModel: SurveyQuestionViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(TestDispatchersProvider.io)
        viewModel = SurveyQuestionViewModel(
            useCase,
            TestDispatchersProvider
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        TestDispatchersProvider.io.cancel()
        viewModel.clear()
    }

    @Test
    fun `when update view model state with a surveys, it returns a list of QuestionUiModel`() =
        runTest {
            viewModel.updateStateWith(MockUtil.mockSurveyApiModel.toSurvey())

            viewModel.viewState
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

        viewModel.updateStateWith(MockUtil.mockSurveyApiModel.toSurvey())
        viewModel.submitAnswer(MockUtil.mockQuestionUiModels)

        viewModel.viewState
            .test {
                val awaitItem = awaitItem()
                awaitItem.isLoading shouldBe false
                awaitItem.isSuccess shouldBe true
            }
    }

    @Test
    fun `when submit answers failed, it returns an error`() = runTest {
        given(useCase)
            .function(useCase::invoke)
            .whenInvokedWith(any())
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )

        viewModel.updateStateWith(MockUtil.mockSurveyApiModel.toSurvey())
        viewModel.submitAnswer(MockUtil.mockQuestionUiModels)

        viewModel.viewState
            .test {
                val errorAwaitItem = awaitItem()
                errorAwaitItem.isLoading shouldBe false
                errorAwaitItem.isSuccess shouldBe false

                cancelAndConsumeRemainingEvents()
            }
    }
}
