package co.nimblehq.avishek.phong.kmmic.android.ui.screen.splash

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.nimble.avishek.phong.kmmic.android.helpers.TestDispatchersProvider
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.domain.usecase.CheckLoggedInUseCase
import co.nimblehq.avishek.phong.kmmic.domain.usecase.LogInUseCase
import co.nimblehq.avishek.phong.kmmic.presentation.module.LogInViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.module.SplashViewModel
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
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
    private val mockLogInUseCase: LogInUseCase = mockk()
    private val mockOnLoginSuccess: () -> Unit = mockk()

    private lateinit var splashViewModel: SplashViewModel
    private lateinit var logInViewModel: LogInViewModel

    @Before
    fun setup() {
        every { mockCheckLoggedInUseCase() } returns false
        every { mockLogInUseCase(any(), any()) } returns flowOf(mockk(relaxed = true))
        every { mockOnLoginSuccess() } just Runs

        splashViewModel = SplashViewModel(mockCheckLoggedInUseCase)
        logInViewModel = LogInViewModel(mockLogInUseCase, TestDispatchersProvider)
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

    @Test
    fun `when logging in successfully, it navigates to the Home screen`() = initComposable {
        waitForAnimationEnd()

        onNodeWithText(context.getString(R.string.login_email)).performTextInput("avishek@nimblehq.co")
        onNodeWithText(context.getString(R.string.login_password)).performTextInput("00000000")
        onNodeWithText(context.getString(R.string.login_button)).performClick()
        waitForIdle()

        verify { mockOnLoginSuccess() }
    }

    @Test
    fun `when logging in fails, it shows an error dialog`() = initComposable {
        val expectedError = Throwable(message = "Your email or password is incorrect. Please try again.")
        every { mockLogInUseCase(any(), any()) } returns flow { throw expectedError }

        waitForAnimationEnd()

        onNodeWithText(context.getString(R.string.login_email)).performTextInput("avishek@nimblehq.co")
        onNodeWithText(context.getString(R.string.login_password)).performTextInput("00000000")
        onNodeWithText(context.getString(R.string.login_button)).performClick()
        waitForIdle()

        onNodeWithText(expectedError.message.orEmpty()).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.ok)).assertIsDisplayed().performClick()
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ApplicationTheme() {
                SplashScreen(
                    splashViewModel = splashViewModel,
                    logInViewModel = logInViewModel,
                    onLoginSuccess = mockOnLoginSuccess
                )
            }
        }
        testBody(composeRule)
    }

    private fun ComposeContentTestRule.waitForAnimationEnd() {
        mainClock.advanceTimeBy(AnimationDurationInMillis)
    }
}
