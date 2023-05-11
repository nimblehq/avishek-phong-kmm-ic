package co.nimblehq.avishek.phong.kmmic.data.repository

import app.cash.turbine.test
import co.nimblehq.avishek.phong.kmmic.data.local.datasource.SurveyLocalDataSource
import co.nimblehq.avishek.phong.kmmic.data.local.model.SurveyRealmObject
import co.nimblehq.avishek.phong.kmmic.data.local.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.data.remote.datasource.SurveyRemoteDataSource
import co.nimblehq.avishek.phong.kmmic.data.remote.model.toSurvey
import co.nimblehq.avishek.phong.kmmic.domain.repository.SurveyRepository
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.times
import io.mockative.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class SurveyRepositoryTest {

    @Mock
    private val mockSurveyRemoteDataSource = mock(classOf<SurveyRemoteDataSource>())
    @Mock
    private val mockSurveyLocalDataSource = mock(classOf<SurveyLocalDataSource>())

    private lateinit var repository: SurveyRepository

    private val mockThrowable = Throwable("mock")
    private val mockSurveyRealmObject = SurveyRealmObject()
        .apply { id = "id" }

    @BeforeTest
    fun setUp() {
        repository = SurveyRepositoryImpl(mockSurveyRemoteDataSource, mockSurveyLocalDataSource)
    }

    @Test
    fun `when there is no cached data and surveys are fetched successfully, it returns surveys one time`() =
        runTest {
            given(mockSurveyLocalDataSource)
                .function(mockSurveyLocalDataSource::getSurveys)
                .whenInvoked()
                .thenReturn(emptyList())
            given(mockSurveyRemoteDataSource)
                .function(mockSurveyRemoteDataSource::getSurveys)
                .whenInvokedWith(any())
                .thenReturn(flowOf(listOf(MockUtil.mockSurveyApiModel)))

            repository.getSurveys(pageNumber = 1, pageSize = 1, isForceLatestData = false).first() shouldBe
                    listOf(MockUtil.mockSurveyApiModel.toSurvey())
        }

    @Test
    fun `when there is cached data and get surveys is succeeded, it returns cached surveys and surveys from API `() =
        runTest {
            given(mockSurveyLocalDataSource)
                .function(mockSurveyLocalDataSource::getSurveys)
                .whenInvoked()
                .thenReturn(listOf(mockSurveyRealmObject))

            given(mockSurveyRemoteDataSource)
                .function(mockSurveyRemoteDataSource::getSurveys)
                .whenInvokedWith(any())
                .thenReturn(flowOf(listOf(MockUtil.mockSurveyApiModel)))

            repository.getSurveys(pageNumber = 1, pageSize = 1, isForceLatestData = false).test {
                this.awaitItem() shouldBe listOf(mockSurveyRealmObject.toSurvey())
                this.awaitItem() shouldBe listOf(MockUtil.mockSurveyApiModel.toSurvey())
                this.awaitComplete()
            }
        }

    @Test
    fun `when force latest data with cached data and get surveys is succeeded, it returns only surveys from API`() =
        runTest {
            given(mockSurveyLocalDataSource)
                .function(mockSurveyLocalDataSource::getSurveys)
                .whenInvoked()
                .thenReturn(listOf(mockSurveyRealmObject))

            given(mockSurveyRemoteDataSource)
                .function(mockSurveyRemoteDataSource::getSurveys)
                .whenInvokedWith(any())
                .thenReturn(flowOf(listOf(MockUtil.mockSurveyApiModel)))

            repository.getSurveys(pageNumber = 1, pageSize = 1, isForceLatestData = true).test {
                this.awaitItem() shouldBe listOf(MockUtil.mockSurveyApiModel.toSurvey())
                this.awaitComplete()
            }

            verify(mockSurveyLocalDataSource).invocation { removeAllSurveys() }
                .wasInvoked(exactly = 1.times)
        }

    @Test
    fun `when there is no cached data and get surveys is failed, it returns error`() =
        runTest {
            given(mockSurveyLocalDataSource)
                .function(mockSurveyLocalDataSource::getSurveys)
                .whenInvoked()
                .thenReturn(emptyList())
            given(mockSurveyRemoteDataSource)
                .function(mockSurveyRemoteDataSource::getSurveys)
                .whenInvokedWith(any())
                .thenReturn(
                    flow {
                        throw mockThrowable
                    }
                )

            repository.getSurveys(pageNumber = 1, pageSize = 1, isForceLatestData = false).test {
                this.awaitError().message shouldBe mockThrowable.message
            }
        }

    @Test
    fun `when there is cached data and get surveys is failed, it returns surveys then an error`() =
        runTest {
            given(mockSurveyLocalDataSource)
                .function(mockSurveyLocalDataSource::getSurveys)
                .whenInvoked()
                .thenReturn(listOf(mockSurveyRealmObject))

            given(mockSurveyRemoteDataSource)
                .function(mockSurveyRemoteDataSource::getSurveys)
                .whenInvokedWith(any())
                .thenReturn(
                    flow {
                        throw mockThrowable
                    }
                )
            repository.getSurveys(pageNumber = 1, pageSize = 1, isForceLatestData = false).test {
                this.awaitItem() shouldBe listOf(mockSurveyRealmObject.toSurvey())
                this.awaitError().message shouldBe mockThrowable.message
            }
        }
}
