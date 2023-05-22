package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.common.*
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.DisplayType.DROPDOWN
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyQuestionUiModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

private const val TopGradientAlpha: Float = 0.01f
private const val BottomGradientAlpha: Float = 0.6f
private const val ImageScale = 1.5f

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurveyQuestionContent(
    surveyQuestionUiModels: List<SurveyQuestionUiModel>,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = surveyQuestionUiModels.firstOrNull()?.imageUrl.orEmpty(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .scale(ImageScale)
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
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 54.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.End)
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .size(28.dp)
                .clip(CircleShape)
                .clickable { onCloseClick() }
        )

        HorizontalPager(
            pageCount = surveyQuestionUiModels.size,
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            QuestionContent(
                page = page + 1,
                count = surveyQuestionUiModels.size,
                question = surveyQuestionUiModels[page],
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(modifier = Modifier
            .wrapContentSize()
            .align(Alignment.End)
            .padding(end = 20.dp)
        ) {
            FloatingActionButton(
                backgroundColor = White,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                modifier = Modifier.size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun QuestionContent(
    page: Int,
    count: Int,
    question: SurveyQuestionUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "$page/$count",
            color = White.copy(alpha = 0.50f),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 20.dp)
        )

        Text(
            text = question.text,
            color = White,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            AnswerContent(
                question = question,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Suppress("ComplexMethod")
@Composable
private fun AnswerContent(
    modifier: Modifier = Modifier,
    question: SurveyQuestionUiModel,
) {
    with(question) {
        when (displayType) {
            DROPDOWN -> Spinner(
                answerUiModels = answerUiModels,
                modifier = modifier.padding(horizontal = 40.dp)
            )
            else -> Unit
        }
    }
}

@Preview
@Composable
fun SurveyQuestionPreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class)
    params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    ApplicationTheme {
        SurveyQuestionContent(
            surveyQuestionUiModels = params.survey.surveyQuestionUiModels,
            onCloseClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
