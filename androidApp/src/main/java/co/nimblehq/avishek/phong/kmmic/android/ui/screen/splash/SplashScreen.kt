package co.nimblehq.avishek.phong.kmmic.android.ui.screen.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.Black60
import kotlinx.coroutines.delay

private const val DELAY_LOGO = 500L
private const val DURATION_LOGO = 1000

@Composable
fun SplashScreen() {
    var shouldShowLogo by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(DELAY_LOGO)
        shouldShowLogo = true
    }

    SplashContent(shouldShowLogo = shouldShowLogo)
}

@Composable
fun SplashContent(
    shouldShowLogo: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        val gradient = Brush.verticalGradient(colors = listOf(Transparent, Black60))
        Box(modifier = modifier.background(gradient))

        AnimatedVisibility(
            visible = shouldShowLogo,
            enter = fadeIn(animationSpec = tween(DURATION_LOGO))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenWithoutLogoPreview() {
    ApplicationTheme {
        SplashContent(shouldShowLogo = false)
    }
}
