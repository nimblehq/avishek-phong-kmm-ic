package co.nimblehq.avishek.phong.kmmic.android.ui.screen.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.common.PrimaryButton
import co.nimblehq.avishek.phong.kmmic.android.ui.common.PrimaryTextField
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import kotlinx.coroutines.delay

private const val LogoDelayInMillis = 500L
private const val LogoDurationInMillis = 750
private const val LoginFormRevealDurationInMillis = 700

private val InitialLogoOffset: Offset = Offset(0f, 0f)
private val FinalLogoOffset = Offset(0f, -230f)
private const val InitialLogoScale: Float = 1f
private const val FinalLogoScale: Float = 0.83f
private const val InitialBlurRadius: Float = 0f
private const val FinalBlurRadius = 25f
private const val InitialAlpha: Float = 0f
private const val FinalAlpha: Float = 1f
private const val InitialTopGradientAlpha: Float = 0.2f
private const val InitialBottomGradientAlpha: Float = 0.6f
private const val BottomGradientAlphaMultiplier: Float = 1 - InitialBottomGradientAlpha

private const val ForgotTextAlpha = 0.5f

@Composable
fun SplashScreen() {
    var shouldShowLogo by remember { mutableStateOf(false) }
    var logoOffset by remember { mutableStateOf(InitialLogoOffset) }
    var logoScale by remember { mutableStateOf(InitialLogoScale) }
    var alpha by remember { mutableStateOf(InitialAlpha) }
    var blurRadius by remember { mutableStateOf(InitialBlurRadius) }
    var shouldShowLoginForm by remember { mutableStateOf(false) }

    val floatTweenSpec = tween<Float>(
        durationMillis = LoginFormRevealDurationInMillis,
        delayMillis = LogoDurationInMillis
    )
    val animateBlur by animateFloatAsState(
        targetValue = blurRadius,
        floatTweenSpec
    )
    val animateAlpha by animateFloatAsState(
        targetValue = alpha,
        floatTweenSpec
    )
    val animateLogoScale by animateFloatAsState(
        targetValue = logoScale,
        floatTweenSpec
    )
    val animateLogoOffset by animateOffsetAsState(
        targetValue = logoOffset,
        tween(
            durationMillis = LoginFormRevealDurationInMillis,
            delayMillis = LogoDurationInMillis
        )
    )

    // Animate logo visibility
    LaunchedEffect(Unit) {
        delay(LogoDelayInMillis)
        shouldShowLogo = true
    }

    // Animate logo position and login form visibility
    LaunchedEffect(Unit) {
        delay(LogoDurationInMillis.toLong())
        shouldShowLoginForm = true
        blurRadius = FinalBlurRadius
        alpha = FinalAlpha
        logoOffset = FinalLogoOffset
        logoScale = FinalLogoScale
    }

    SplashContent(
        shouldShowLogo = shouldShowLogo,
        animateAlpha = animateAlpha,
        animateLogoScale = animateLogoScale,
        animateLogoOffset = animateLogoOffset,
        animateBlur = animateBlur
    )

    if (shouldShowLoginForm) {
        LoginForm(
            modifier = Modifier.alpha(animateAlpha),
            onLogInClick = { email, passsword ->
                // TODO: implement in the integrate PR
            }
        )
    }
}

@Composable
fun SplashContent(
    shouldShowLogo: Boolean,
    modifier: Modifier = Modifier,
    animateAlpha: Float,
    animateLogoScale: Float,
    animateLogoOffset: Offset,
    animateBlur: Float,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_splash),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .blur(animateBlur.dp)
            )

            val gradient = Brush.verticalGradient(
                colors = listOf(
                    Black.copy(alpha = animateAlpha * InitialTopGradientAlpha),
                    Black.copy(
                        alpha = (animateAlpha * BottomGradientAlphaMultiplier) +
                            InitialBottomGradientAlpha
                    )
                )
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(gradient)
            )

            AnimatedVisibility(
                visible = shouldShowLogo,
                enter = fadeIn(animationSpec = tween(LogoDurationInMillis))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_white),
                    contentDescription = null,
                    modifier = Modifier
                        .offset(animateLogoOffset.x.dp, animateLogoOffset.y.dp)
                        .scale(animateLogoScale)
                )
            }
        }
    }
}

@Composable
private fun LoginForm(
    onLogInClick: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(horizontal = 24.dp)
    ) {
        PrimaryTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = stringResource(id = R.string.login_email),
            keyboardType = KeyboardType.Email,
        )
        Box {
            PrimaryTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(id = R.string.login_password),
                visualTransformation = PasswordVisualTransformation(),
                imeAction = ImeAction.Done,
            )
            Text(
                text = stringResource(id = R.string.login_forgot),
                color = MaterialTheme.colors.onSurface.copy(alpha = ForgotTextAlpha),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterEnd)
                    .padding(end = 18.dp)
            )
        }
        PrimaryButton(
            text = stringResource(id = R.string.login_button),
            onClick = { onLogInClick(email, password) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun SplashContentPreview() {
    ApplicationTheme {
        SplashContent(
            shouldShowLogo = true,
            animateAlpha = InitialAlpha,
            animateLogoScale = InitialLogoScale,
            animateLogoOffset = InitialLogoOffset,
            animateBlur = InitialBlurRadius
        )
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    ApplicationTheme {
        LoginForm(
            onLogInClick = { _, _ -> }
        )
    }
}
