package co.nimblehq.avishek.phong.kmmic.android.ui.screen.thankyou

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class ThankYouScreenTest {

    @get:Rule
    val composeRule: ComposeContentTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when entering the Thank You screen, it displays the UI elements accordingly`() {
        composeRule.mainClock.autoAdvance = false
        initComposable {
            waitForAnimationEnd()

            onNodeWithText(context.getString(R.string.thank_you_message)).assertIsDisplayed()
        }
    }

    private fun initComposable(testBody: ComposeContentTestRule.() -> Unit) {
        composeRule.setContent {
            ApplicationTheme() {
                ThankYouScreen(
                    onComplete = {}
                )
            }
        }
        testBody(composeRule)
    }

    private fun ComposeContentTestRule.waitForAnimationEnd() {
        mainClock.advanceTimeBy(FadeAnimationDurationInMillis.toLong())
    }
}
