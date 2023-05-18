package co.nimblehq.avishek.phong.kmmic.presentation.module

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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.takeWhile
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

    @Test
    fun `when getSurveyDetailUseCase is invoked successfully, it returns a survey`() = runTest {
        given(getSurveyDetailUseCase)
            .function(getSurveyDetailUseCase::invoke)
            .whenInvokedWith(any())
            .thenReturn(flowOf(MockUtil.mockSurvey))

        viewModel.fetchSurveyDetail("survey_id")

        viewModel.viewSate.takeWhile { !it.isLoading }
            .collect {
                it.isLoading shouldBe false
                it.errorMessage shouldBe null
                it.survey?.id shouldBe MockUtil.mockSurvey.id
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

        viewModel.viewSate.takeWhile { !it.isLoading }
            .catch {
                it.message shouldBe MockUtil.mockThrowable.message
            }
            .collect()
    }
}
