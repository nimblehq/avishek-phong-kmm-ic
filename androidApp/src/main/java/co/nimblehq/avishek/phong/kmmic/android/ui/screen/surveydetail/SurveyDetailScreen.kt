package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.common.AlertDialog
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType.INTRO
import co.nimblehq.avishek.phong.kmmic.presentation.module.HomeViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.module.SurveyDetailViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

const val InitialImageScale: Float = 1f
const val FinalImageScale: Float = 1.5f
const val ImageScaleAnimationDurationInMillis = 700

@Suppress("LongMethod", "MagicNumber")
@Composable
fun SurveyDetailScreen(
    homeViewModel: HomeViewModel = getViewModel(),
    surveyDetailViewModel: SurveyDetailViewModel = getViewModel(),
    surveyId: String,
    onBackClick: () -> Unit,
    onAnswersSubmitted: () -> Unit,
) {
    val homeViewState by homeViewModel.viewState.collectAsStateWithLifecycle()
    val surveyDetailViewState by surveyDetailViewModel.viewSate.collectAsStateWithLifecycle()
    var shouldShowStartContent by remember { mutableStateOf(false) }
    var shouldShowSurveyQuestionContent by remember { mutableStateOf(false) }
    var imageScale by remember { mutableStateOf(InitialImageScale) }
    val coroutineScope = rememberCoroutineScope()
    val surveyUiModel = homeViewState.surveys.find { it.id == surveyId }
    val surveyWithoutIntro = surveyDetailViewState.survey?.run {
        copy(questions = questions?.filter { it.displayType != INTRO })
    }
    var shouldShowExitConfirmationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        surveyDetailViewModel.fetchSurveyDetail(surveyId)
        imageScale = FinalImageScale
        shouldShowStartContent = true
    }

    SurveyDetailContent(
        surveyUiModel = surveyUiModel,
        questionUiModels = surveyWithoutIntro?.toSurveyUiModel()?.questionUiModels,
        shouldShowStartContent = shouldShowStartContent,
        imageScale = imageScale,
        shouldShowSurveyQuestionContent = shouldShowSurveyQuestionContent,
        onBackClick = {
            coroutineScope.launch {
                imageScale = InitialImageScale
                shouldShowStartContent = false
                delay(ImageScaleAnimationDurationInMillis.toLong())
                onBackClick()
            }
        },
        onStartSurveyClick = {
            shouldShowSurveyQuestionContent = true
        },
        onCloseClick = {
            shouldShowExitConfirmationDialog = true
        },
        onAnswersSubmitted = onAnswersSubmitted
    )

    if (surveyDetailViewState.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }

    if (shouldShowExitConfirmationDialog) {
        AlertDialog(
            title = LocalContext.current.getString(R.string.warning),
            message = LocalContext.current.getString(R.string.quit_survey_message),
            onYesClick = onBackClick,
            onCancelClick = {
                shouldShowExitConfirmationDialog = false
            }
        )
    }
}

@Composable
fun SurveyDetailContent(
    surveyUiModel: SurveyUiModel?,
    questionUiModels: List<QuestionUiModel>?,
    shouldShowStartContent: Boolean,
    shouldShowSurveyQuestionContent: Boolean,
    onBackClick: () -> Unit,
    onStartSurveyClick: () -> Unit,
    onCloseClick: () -> Unit,
    onAnswersSubmitted: () -> Unit,
    imageScale: Float,
) {
    surveyUiModel?.let {
        SurveyStartContent(
            surveyUiModel = it,
            shouldShowContent = shouldShowStartContent,
            imageScale = imageScale,
            animationDurationInMillis = ImageScaleAnimationDurationInMillis,
            onStartClick = onStartSurveyClick,
            onBackClick = onBackClick
        )
    }

    if (shouldShowSurveyQuestionContent && questionUiModels?.isNotEmpty() == true) {
        SurveyQuestionContent(
            backgroundImageUrl = surveyUiModel?.largeImageUrl.orEmpty(),
            questionUiModels = questionUiModels,
            onCloseClick = onCloseClick,
            onSubmitClick = {
                //TODO: invoke after the submission is successful in the integrate task
                onAnswersSubmitted()
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(
    name = "Intro",
    showSystemUi = true
)
@Composable
fun SurveyDetailScreenStartPagePreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class)
    params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme {
            SurveyDetailContent(
                surveyUiModel = survey,
                questionUiModels = survey.questionUiModels,
                shouldShowStartContent = true,
                shouldShowSurveyQuestionContent = false,
                onBackClick = {},
                onStartSurveyClick = {},
                onCloseClick = {},
                onAnswersSubmitted = {},
                imageScale = FinalImageScale
            )
        }
    }
}

@Preview(
    name = "Question",
    showSystemUi = true
)
@Composable
fun SurveyDetailScreenQuestionPagePreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class)
    params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme {
            SurveyDetailContent(
                surveyUiModel = survey,
                questionUiModels = survey.questionUiModels,
                shouldShowStartContent = false,
                shouldShowSurveyQuestionContent = true,
                onBackClick = {},
                onStartSurveyClick = {},
                onCloseClick = {},
                onAnswersSubmitted = { },
                imageScale = FinalImageScale
            )
        }
    }
}
