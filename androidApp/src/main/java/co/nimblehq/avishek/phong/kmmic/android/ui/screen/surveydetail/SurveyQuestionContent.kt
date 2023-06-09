package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.common.*
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType.*
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.*
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

private const val TopGradientAlpha: Float = 0.01f
private const val BottomGradientAlpha: Float = 0.6f
private const val ImageScale = 1.5f

@Suppress("LongMethod")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurveyQuestionContent(
    backgroundImageUrl: String,
    questionUiModels: List<QuestionUiModel>,
    onCloseClick: () -> Unit,
    onSubmitClick: () -> Unit,
    onQuestionAnswered: (surveyQuestionUiModel: QuestionUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImageUrl,
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

    val questionCount = questionUiModels.size

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
            pageCount = questionCount,
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            QuestionContent(
                questionUiModel = questionUiModels[page],
                onQuestionAnswered = onQuestionAnswered,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (pagerState.currentPage < questionCount - 1) {
            FloatingActionButton(
                backgroundColor = White,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp)
                    .size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )
            }
        } else {
            PrimaryButton(
                text = stringResource(id = R.string.survey_question_submit),
                onClick = onSubmitClick,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp)
                    .wrapContentWidth()
            )
        }
    }

    BackHandler(true) {
        onCloseClick()
    }
}

@Composable
private fun QuestionContent(
    questionUiModel: QuestionUiModel,
    onQuestionAnswered: (questionUiModel: QuestionUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = questionUiModel.step,
            color = White.copy(alpha = 0.50f),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 20.dp)
        )

        Text(
            text = questionUiModel.questionTitle,
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
                questionUiModel = questionUiModel,
                onQuestionAnswered = onQuestionAnswered,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Suppress("ComplexMethod")
@Composable
private fun AnswerContent(
    questionUiModel: QuestionUiModel,
    onQuestionAnswered: (questionUiModel: QuestionUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    with(questionUiModel) {
        val onAnswerProvided: (surveyAnswerUiModel: SurveyAnswerUiModel) -> Unit =
            { onQuestionAnswered(questionUiModel.copy(userInputs = setOf(it.toUserInput()))) }
        val onAnswersProvided: (answerUiModels: List<SurveyAnswerUiModel>) -> Unit = { surveyAnswerUiModels ->
            onQuestionAnswered(
                questionUiModel.copy(
                    userInputs = surveyAnswerUiModels
                        .map { it.toUserInput() }
                        .toSet()
                )
            )
        }
        when (displayType) {
            DROPDOWN -> Spinner(
                surveyAnswerUiModels = answers,
                onAnswerSelected = onAnswerProvided,
                modifier = modifier.padding(horizontal = 40.dp)
            )
            STAR,
            HEART,
            SMILEY,
            -> RatingBar(
                answerUiModels = answers,
                emojis = displayType.toEmojis(answers.size),
                onAnswerSelected = onAnswerProvided,
                isRangeSelectable = displayType != SMILEY,
                modifier = modifier
            )
            TEXTAREA -> TextArea(
                surveyAnswerUiModel = questionUiModel.answers.first(),
                onAnswerProvided = onAnswerProvided,
                modifier = modifier
                    .padding(horizontal = 24.dp)
                    .heightIn(168.dp)
            )
            TEXTFIELD -> TextFields(
                surveyAnswerUiModels = questionUiModel.answers,
                onAnswersProvided = onAnswersProvided,
                modifier = modifier.padding(horizontal = 24.dp)
            )
            CHOICE -> MultiChoiceForm(
                surveyAnswerUiModels = questionUiModel.answers,
                onAnswersChecked = onAnswersProvided,
                modifier = modifier.padding(horizontal = 24.dp)
            )
            NPS -> NpsBar(
                surveyAnswerUiModels = questionUiModel.answers,
                onAnswerSelected = onAnswerProvided,
                modifier = modifier
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
            backgroundImageUrl = params.survey.largeImageUrl,
            questionUiModels = params.survey.questionUiModels,
            onCloseClick = {},
            onSubmitClick = {},
            onQuestionAnswered = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
