package co.nimblehq.avishek.phong.kmmic.presentation.module

import app.cash.turbine.test
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetSurveyDetailUseCase
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


@ExperimentalCoroutinesApi
class SurveyDetailViewModelTest {

    @Mock
    val getSurveyDetailUseCase = mock(classOf<GetSurveyDetailUseCase>())
    private lateinit var viewModel: SurveyDetailViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeTest
    fun setUp() {
        viewModel = SurveyDetailViewModel(getSurveyDetailUseCase)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        viewModel.clear()
    }

    // TODO: re-enable test after solving test unable to pass on CI

    /**
    @Test
    fun `when getSurveyDetailUseCase is invoked successfully, it returns a survey`() = runTest {
        given(getSurveyDetailUseCase)
            .function(getSurveyDetailUseCase::invoke)
            .whenInvokedWith(any())
            .thenReturn(flowOf(MockUtil.mockSurvey))

        viewModel.fetchSurveyDetail("survey_id")

        viewModel.viewSate
            .test {
                awaitItem() shouldBe SurveyDetailViewState(
                    isLoading = true,
                    errorMessage = null,
                    survey = null
                )
                awaitItem() shouldBe SurveyDetailViewState(
                    isLoading = false,
                    errorMessage = null,
                    survey = MockUtil.mockSurvey
                )
            }
    }

    @Test
    fun `when getSurveyDetailUseCase returns a failure, it returns an error`() = runTest {
        given(getSurveyDetailUseCase)
            .function(getSurveyDetailUseCase::invoke)
            .whenInvokedWith(any())
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )

        viewModel.fetchSurveyDetail("survey_id")

        viewModel.viewSate
            .test {
                awaitItem() shouldBe SurveyDetailViewState(
                    isLoading = true,
                    errorMessage = null,
                    survey = null
                )
                awaitItem() shouldBe SurveyDetailViewState(
                    isLoading = false,
                    errorMessage = MockUtil.mockThrowable.message,
                    survey = null
                )
            }
    }
    **/
}
