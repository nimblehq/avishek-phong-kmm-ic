package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetSurveysUseCaseTest {

    @Mock
    private val mockRepository = mock(classOf<SurveyRepository>())

    private lateinit var useCase: GetSurveysUseCase

    private val mockThrowable = Throwable("mock")
    private val mockSurvey = Survey(
        "id",
        "title",
        "description",
        true,
        "coverImageUrl"
    )

    @BeforeTest
    fun setUp() {
        useCase = GetSurveysUseCaseImpl(mockRepository)
    }

    @Test
    fun `when getSurveys is called successfully, it returns a list of surveys`() = runTest {
        given(mockRepository)
            .function(mockRepository::getSurveys)
            .whenInvokedWith(any(), any(), any())
            .thenReturn(
                flow {
                    emit(listOf(mockSurvey))
                }
            )

        useCase(1, 1, false).collect {
            it shouldBe listOf(mockSurvey)
        }
    }

    @Test
    fun `when getSurveys is called but returns an error, it returns an error`() = runTest {
        given(mockRepository)
            .function(mockRepository::getSurveys)
            .whenInvokedWith(any(), any(), any())
            .thenReturn(
                flow {
                    throw mockThrowable
                }
            )

        useCase(1, 1, false)
            .catch {
                it.message shouldBe mockThrowable.message
            }
            .collect()
    }
}
