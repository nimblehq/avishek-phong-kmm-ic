package co.nimblehq.avishek.phong.kmmic.android.ui.screen.thankyou

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

const val FadeAnimationDurationInMillis = 500

@Composable
fun ThankYouScreen(
    onComplete: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.thank_you))
    val progress by animateLottieCompositionAsState(composition)
    var shouldShowContent by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = shouldShowContent,
        enter = fadeIn(animationSpec = tween(FadeAnimationDurationInMillis)),
        exit = fadeOut(animationSpec = tween(FadeAnimationDurationInMillis)),
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = stringResource(R.string.thank_you_message),
                color = Color.White,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }

    LaunchedEffect(progress) {
        if (progress == 1f) {
            shouldShowContent = false
            delay(FadeAnimationDurationInMillis.toLong())
            onComplete()
        }
    }
}

@Preview
@Composable
fun ThankYouScreenPreview() {
    ApplicationTheme {
        ThankYouScreen {}
    }
}
