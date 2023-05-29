package co.nimblehq.avishek.phong.kmmic.domain.usecase

import co.nimblehq.avishek.phong.kmmic.domain.model.SurveySubmission
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SubmitSurveyAnswerUseCaseTest {

    @Mock
    private val mockRepository = mock(classOf<SurveyRepository>())

    private val mockThrowable = Throwable("mock")

    private lateinit var useCase: SubmitSurveyAnswerUseCase

    @BeforeTest
    fun setUp() {
        useCase = SubmitSurveyAnswerUseCaseImpl(mockRepository)
    }

    @Test
    fun `when the use case is succeeded, it returns unit`() = runTest {
        given(mockRepository)
            .function(mockRepository::submitSurvey)
            .whenInvokedWith(any())
            .thenReturn(flowOf(Unit))

        useCase(SurveySubmission("survey_id", emptyList())).collect {
            it shouldBe Unit
        }
    }

    @Test
    fun `when the use case is failed, it returns error`() = runTest {
        given(mockRepository)
            .function(mockRepository::submitSurvey)
            .whenInvokedWith(any())
            .thenReturn(flow { throw mockThrowable })

        useCase(SurveySubmission("survey_id", emptyList()))
            .catch {
                it.message shouldBe mockThrowable.message
            }
            .collect()
    }
}
