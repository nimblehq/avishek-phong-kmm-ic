package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTime
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetSurveysUseCase
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetUserProfileUseCase
import co.nimblehq.avishek.phong.kmmic.helper.MockUtil
import io.mockative.any
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val mockGetUserProfileUseCase = mock(classOf<GetUserProfileUseCase>())
    private val mockGetSurveysUseCase = mock(classOf<GetSurveysUseCase>())
    private val mockDateTime = mock(classOf<DateTime>())
    private val mockDateTimeFormatter = mock(classOf<DateTimeFormatter>())
    private

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
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
    }

    @Test
    fun `when`() = runTest {
        given(mockGetUserProfileUseCase)
            .function(mockGetUserProfileUseCase::invoke)
            .whenInvoked()
            .thenReturn(flowOf(MockUtil.mockUser))

        given(mockGetSurveysUseCase)
            .function(mockGetSurveysUseCase::invoke)
            .whenInvokedWith(any(), any(), any())
            .thenReturn(flowOf(listOf(MockUtil.mockSurvey)))

        viewModel.fetchData().
    }


}
