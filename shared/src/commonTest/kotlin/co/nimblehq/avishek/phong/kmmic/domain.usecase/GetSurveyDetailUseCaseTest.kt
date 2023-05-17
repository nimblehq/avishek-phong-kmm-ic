package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.data.remote.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetSurveyDetailUseCaseTest {

    @Mock
    private val mockRepository = mock(classOf<SurveyRepository>())

    private lateinit var useCase: GetSurveyDetailUseCase

    @BeforeTest
    fun setUp() {
        useCase = GetSurveyDetailUseCaseImpl(mockRepository)
    }

    @Test
    fun `when getSurvey is called successfully, it returns a survey`() = runTest {
        given(mockRepository)
            .function(mockRepository::getSurvey)
            .whenInvokedWith(any())
            .thenReturn(flowOf(MockUtil.mockSurveyApiModel.toSurvey()))

        useCase("survey_id").collect {
            it shouldBe MockUtil.mockSurveyApiModel.toSurvey()
        }
    }

    @Test
    fun `when getSurvey is called but returns an error, it returns an error`() = runTest {
        given(mockRepository)
            .function(mockRepository::getSurvey)
            .whenInvokedWith(any())
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )

        useCase("survey_id")
            .catch {
                it.message shouldBe MockUtil.mockThrowable.message
            }
            .collect()
    }
}
