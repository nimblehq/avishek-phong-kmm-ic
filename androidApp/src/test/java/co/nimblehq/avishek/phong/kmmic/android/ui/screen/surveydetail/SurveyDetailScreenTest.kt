package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.nimble.avishek.phong.kmmic.android.helpers.TestDispatchersProvider
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.usecase.*
import co.nimblehq.avishek.phong.kmmic.presentation.module.*
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class SurveyDetailScreenTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val mockHomeViewModel: HomeViewModel = mockk()
    private val mockGetSurveyDetailUseCase: GetSurveyDetailUseCase = mockk()
    private val mockOnBackClick: () -> Unit = mockk()

    private val surveyDetailViewModel = SurveyDetailViewModel(
        mockGetSurveyDetailUseCase,
        TestDispatchersProvider
    )

    private val mockSurvey = Survey(
        id = "1",
        title = "Title",
        description = "Description",
        coverImageUrl = "ImageUrl",
        isActive = true
    )
    private val mockHomeViewState = HomeViewState(
        isLoading = false,
        isRefreshing = false,
        surveys = listOf(SurveyUiModel(mockSurvey))
    )

    @Before
    fun setUp() {
        every { mockHomeViewModel.viewState } returns MutableStateFlow(mockHomeViewState)
        every { mockGetSurveyDetailUseCase(any()) } returns flowOf(mockSurvey)
        every { mockOnBackClick() } just Runs
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when entering the survey detail screen, it shows the UI accordingly`() = initComposable {
        waitForAnimationEnd()

        onNodeWithText(mockSurvey.title).assertIsDisplayed()
        onNodeWithText(mockSurvey.description).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.survey_detail_start)).assertIsDisplayed()
        onNodeWithContentDescription(BackButtonContentDescription).assertIsDisplayed()
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ApplicationTheme() {
                SurveyDetailScreen(
                    homeViewModel = mockHomeViewModel,
                    surveyDetailViewModel = surveyDetailViewModel,
                    surveyId = mockSurvey.id,
                    onBackClick = mockOnBackClick,
                    onAnswersSubmitted = {}
                )
            }
        }
        testBody(composeRule)
    }

    private fun ComposeContentTestRule.waitForAnimationEnd() {
        mainClock.advanceTimeBy(ImageScaleAnimationDurationInMillis.toLong())
    }
}
