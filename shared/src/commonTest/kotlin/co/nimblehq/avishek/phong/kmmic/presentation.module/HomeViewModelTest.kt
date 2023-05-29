package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.BuildKonfig
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeImpl
import co.nimblehq.avishek.phong.kmmic.domain.usecase.*
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import kotlin.test.*

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @Mock
    private val mockGetUserProfileUseCase = mock(classOf<GetUserProfileUseCase>())

    @Mock
    private val mockGetSurveysUseCase = mock(classOf<GetSurveysUseCase>())

    @Mock
    private val mockGetAppVersionUseCase = mock(classOf<GetAppVersionUseCase>())

    @Mock
    private val mockDateTimeFormatter = mock(classOf<DateTimeFormatter>())

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockDateTime = DateTimeImpl()
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
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

        given(mockGetAppVersionUseCase)
            .function(mockGetAppVersionUseCase::invoke)
            .whenInvoked()
            .thenReturn(BuildKonfig.VERSION_CODE)
        
        viewModel = HomeViewModel(
            mockGetUserProfileUseCase,
            mockGetSurveysUseCase,
            mockGetAppVersionUseCase,
            mockDateTime,
            mockDateTimeFormatter
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        viewModel.clear()
    }

    @Test
    fun `when fetchData is called successfully, it returns the user's avatar, current date and surveys`() = runTest {
        viewModel.fetchData()

        viewModel.viewState.takeWhile { !it.isLoading }.collect {
            it.headerUiModel?.dateText shouldBe "Monday, May 12"
            it.headerUiModel?.imageUrl shouldBe MockUtil.mockUser.avatarUrl
            it.surveys shouldBe listOf(SurveyUiModel(MockUtil.mockSurvey))
        }
        viewModel.appVersion.first() shouldBe BuildKonfig.VERSION_CODE
    }

    @Test
    fun `when fetchData is called and getUserProfileUseCase returns failure, it returns surveys and null for the user's avatar`() =
        runTest {
            given(mockGetUserProfileUseCase)
                .function(mockGetUserProfileUseCase::invoke)
                .whenInvoked()
                .thenReturn(
                    flow {
                        throw MockUtil.mockThrowable
                    }
                )

            viewModel.fetchData()

            viewModel.viewState.takeWhile { !it.isLoading }.collect {
                it.headerUiModel?.imageUrl shouldBe null
                it.headerUiModel?.dateText shouldBe "Monday, May 12"
                it.surveys shouldBe listOf(SurveyUiModel(MockUtil.mockSurvey))
            }
        }

    @Test
    fun `when fetchData is called and getSurveyUseCase returns failure, it returns an empty array of surveys and the user's avatar`() =
        runTest {
            given(mockGetSurveysUseCase)
                .function(mockGetSurveysUseCase::invoke)
                .whenInvokedWith(any(), any(), any())
                .thenReturn(
                    flow {
                        throw MockUtil.mockThrowable
                    }
                )

            viewModel.fetchData()

            viewModel.viewState.takeWhile { !it.isLoading }.collect {
                it.headerUiModel?.imageUrl shouldBe MockUtil.mockUser.avatarUrl
                it.headerUiModel?.dateText shouldBe "Monday, May 12"
                it.surveys shouldBe emptyList()
            }
        }

    @Test
    fun `when both getUserProfileUseCase and getSurveysUseCase returns a failure, it only returns the date time`() =
        runTest {
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

            viewModel.fetchData()

            viewModel.viewState.takeWhile { !it.isLoading }.collect {
                it.headerUiModel?.imageUrl shouldBe ""
                it.headerUiModel?.dateText shouldBe "Monday, May 12"
                it.surveys shouldBe emptyList()
            }
        }

    @Test
    fun `when refreshing the surveys successfully, it emits surveys accordingly`() = runTest {
        viewModel.refresh()

        viewModel.viewState.takeWhile { !it.isRefreshing && !it.isLoading }.collect {
            it.surveys shouldBe listOf(SurveyUiModel(MockUtil.mockSurvey))
        }
    }
}
