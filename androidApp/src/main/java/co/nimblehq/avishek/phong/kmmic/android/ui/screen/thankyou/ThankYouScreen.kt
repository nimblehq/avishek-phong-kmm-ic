package co.nimblehq.avishek.phong.kmmic.android.ui.screen.thankyou

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun ThankYouScreen(
    onComplete: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.thank_you))
    val progress by animateLottieCompositionAsState(composition)

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.thank_you_message),
            color = Color.White,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }

    if (progress == 1f) {
        onComplete()
    }
}

@Preview
@Composable
fun ThankYouScreenPreview() {
    ApplicationTheme {
        ThankYouScreen {}
    }
}
