package co.nimblehq.avishek.phong.kmmic.android.ui.screen.splash

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.domain.usecase.CheckLoggedInUseCase
import co.nimblehq.avishek.phong.kmmic.presentation.module.SplashViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

private const val AnimationDurationInMillis = LogoDelayInMillis + LogoDurationInMillis + LoginFormRevealDurationInMillis

@RunWith(AndroidJUnit4::class)
internal class SplashScreenTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val mockCheckLoggedInUseCase: CheckLoggedInUseCase = mockk()

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setup() {
        every { mockCheckLoggedInUseCase() } returns false

        splashViewModel = SplashViewModel(mockCheckLoggedInUseCase)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when entering the Splash screen as a logged out user, it shows UI correctly`() = initComposable {
        waitForAnimationEnd()

        onNodeWithContentDescription(LogoImageContentDescription).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_email)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_password)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_forgot)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.login_button)).assertIsDisplayed()
    }

    @Test
    fun `when entering the Splash screen as a logged in user, it shows UI correctly`() {
        every { mockCheckLoggedInUseCase() } returns true

        initComposable {
            waitForAnimationEnd()

            onNodeWithContentDescription(LogoImageContentDescription).assertIsDisplayed()
        }
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ApplicationTheme() {
                SplashScreen(splashViewModel = splashViewModel)
            }
        }
        testBody(composeRule)
    }

    private fun ComposeContentTestRule.waitForAnimationEnd() {
        mainClock.advanceTimeBy(AnimationDurationInMillis)
    }

    private fun onNodeWithDrawable(@DrawableRes drawableId: Int): SemanticsNodeInteraction {
        return composeRule.onNode(
            SemanticsMatcher.expectValue(
                SemanticsPropertyKey("DrawableResId"),
                drawableId
            )
        )
    }
}
