package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTime
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeImpl
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetSurveysUseCase
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetUserProfileUseCase
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @Mock
    private val mockGetUserProfileUseCase = mock(classOf<GetUserProfileUseCase>())
    @Mock
    private val mockGetSurveysUseCase = mock(classOf<GetSurveysUseCase>())
    @Mock
    private val mockDateTimeFormatter = mock(classOf<DateTimeFormatter>())

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockDateTime = DateTimeImpl()
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setUp() {
        viewModel = HomeViewModel(
            mockGetUserProfileUseCase,
            mockGetSurveysUseCase,
            mockDateTime,
            mockDateTimeFormatter
        )
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        viewModel.clear()
    }

    @Test
    fun `when fetchData is called successfully, it returns the user's avatar, current date and surveys`() = runTest {
        given(mockGetUserProfileUseCase)
            .function(mockGetUserProfileUseCase::invoke)
            .whenInvoked()
            .thenReturn(flowOf(MockUtil.mockUser))

        given(mockGetSurveysUseCase)
            .function(mockGetSurveysUseCase::invoke)
            .whenInvokedWith(any(), any(), any())
            .thenReturn(flowOf(listOf(MockUtil.mockSurvey)))

        given(mockDateTimeFormatter)
            .function(mockDateTimeFormatter::getFormattedString)
            .whenInvokedWith(any(), any())
            .thenReturn("Monday, May 12")

        viewModel.fetchData()

        viewModel.viewState.takeWhile { !it.isLoading }.collect {
            it.headerUiModel?.dateText shouldBe "Monday, May 12"
            it.headerUiModel?.imageUrl shouldBe MockUtil.mockUser.avatarUrl
            it.surveys shouldBe listOf(SurveyUiModel(MockUtil.mockSurvey))
        }
    }

    @Test
    fun `when fetchData is called and getUserProfileUseCase returns failure, it returns surveys and null for the user's avatar`() = runTest {
        given(mockGetUserProfileUseCase)
            .function(mockGetUserProfileUseCase::invoke)
            .whenInvoked()
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )

        given(mockGetSurveysUseCase)
            .function(mockGetSurveysUseCase::invoke)
            .whenInvokedWith(any(), any(), any())
            .thenReturn(flowOf(listOf(MockUtil.mockSurvey)))

        given(mockDateTimeFormatter)
            .function(mockDateTimeFormatter::getFormattedString)
            .whenInvokedWith(any(), any())
            .thenReturn("Monday, May 12")

        viewModel.fetchData()

        viewModel.viewState.takeWhile { !it.isLoading }.collect {
            it.headerUiModel?.imageUrl shouldBe null
            it.headerUiModel?.dateText shouldBe "Monday, May 12"
            it.surveys shouldBe listOf(SurveyUiModel(MockUtil.mockSurvey))
        }
    }

    @Test
    fun `when fetchData is called and getSurveyUseCase returns failure, it returns an empty array of surveys and the user's avatar`() = runTest {
        given(mockGetUserProfileUseCase)
            .function(mockGetUserProfileUseCase::invoke)
            .whenInvoked()
            .thenReturn(flowOf(MockUtil.mockUser))

        given(mockGetSurveysUseCase)
            .function(mockGetSurveysUseCase::invoke)
            .whenInvokedWith(any(), any(), any())
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )

        given(mockDateTimeFormatter)
            .function(mockDateTimeFormatter::getFormattedString)
            .whenInvokedWith(any(), any())
            .thenReturn("Monday, May 12")

        viewModel.fetchData()

        viewModel.viewState.takeWhile { !it.isLoading }.collect {
            it.headerUiModel?.imageUrl shouldBe MockUtil.mockUser.avatarUrl
            it.headerUiModel?.dateText shouldBe "Monday, May 12"
            it.surveys shouldBe emptyList()
        }
    }

    @Test
    fun `when both getUserProfileUseCase and getSurveysUseCase returns a failure, it only returns the date time`() = runTest {
        given(mockGetUserProfileUseCase)
            .function(mockGetUserProfileUseCase::invoke)
            .whenInvoked()
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )

        given(mockGetSurveysUseCase)
            .function(mockGetSurveysUseCase::invoke)
            .whenInvokedWith(any(), any(), any())
            .thenReturn(
                flow {
                    throw MockUtil.mockThrowable
                }
            )

        given(mockDateTimeFormatter)
            .function(mockDateTimeFormatter::getFormattedString)
            .whenInvokedWith(any(), any())
            .thenReturn("Monday, May 12")

        viewModel.fetchData()

        viewModel.viewState.takeWhile { !it.isLoading }.collect {
            it.headerUiModel?.imageUrl shouldBe ""
            it.headerUiModel?.dateText shouldBe "Monday, May 12"
            it.surveys shouldBe emptyList()
        }
    }
}
