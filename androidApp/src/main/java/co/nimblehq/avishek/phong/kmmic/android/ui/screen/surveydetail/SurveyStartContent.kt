package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.common.PrimaryButton
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel
import coil.compose.AsyncImage
import kotlinx.coroutines.*

private const val TopGradientAlpha: Float = 0.01f
private const val BottomGradientAlpha: Float = 0.6f
private const val InitialImageScale: Float = 1f
private const val FinalImageScale: Float = 1.5f
const val ImageScaleAnimationDurationInMillis = 700

@Composable
fun SurveyStartContent(
    surveyUiModel: SurveyUiModel,
    onStartClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    var shouldShowContent by remember { mutableStateOf(false) }
    var imageScale by remember { mutableStateOf(InitialImageScale) }
    val scope = rememberCoroutineScope()

    val backNavigationHandler: suspend CoroutineScope.() -> Unit = {
        imageScale = InitialImageScale
        shouldShowContent = false
        delay(ImageScaleAnimationDurationInMillis.toLong())
        onBackClick()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val floatTweenSpec = tween<Float>(
            durationMillis = ImageScaleAnimationDurationInMillis
        )
        val animateImageScale by animateFloatAsState(
            targetValue = imageScale,
            floatTweenSpec
        )
        AsyncImage(
            model = surveyUiModel.largeImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .scale(animateImageScale)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = TopGradientAlpha),
                            Color.Black.copy(alpha = BottomGradientAlpha)
                        )
                    )
                )
        )

        LaunchedEffect(Unit) {
            imageScale = FinalImageScale
            shouldShowContent = true
        }

        BackHandler(true) {
            scope.launch(block = backNavigationHandler)
        }

        AnimatedVisibility(
            visible = shouldShowContent,
            enter = fadeIn(animationSpec = tween(ImageScaleAnimationDurationInMillis)),
            exit = fadeOut(animationSpec = tween(ImageScaleAnimationDurationInMillis)),
            modifier = Modifier
                .matchParentSize()
                .statusBarsPadding()
        ) {
            Column(
                modifier = Modifier.matchParentSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(12.dp)
                        .wrapContentSize()
                        .clickable { scope.launch(block = backNavigationHandler) }
                        .padding(all = 8.dp)
                )

                Text(
                    text = surveyUiModel.title,
                    color = Color.White,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .padding(horizontal = 20.dp)
                )

                Text(
                    text = surveyUiModel.description,
                    color = Color.White.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                PrimaryButton(
                    text = stringResource(id = R.string.survey_detail_start),
                    onClick = onStartClick,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.End)
                        .padding(bottom = 60.dp, end = 20.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SurveyIntroPreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class) params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    ApplicationTheme {
        SurveyStartContent(
            surveyUiModel = params.survey,
            onStartClick = {},
            onBackClick = {}
        )
    }
}
