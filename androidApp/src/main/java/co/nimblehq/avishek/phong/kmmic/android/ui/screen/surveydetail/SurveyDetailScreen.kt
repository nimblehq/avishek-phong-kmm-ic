package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.module.HomeViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

const val InitialImageScale: Float = 1f
const val FinalImageScale: Float = 1.5f
private const val ImageScaleAnimationDurationInMillis = 700

@Composable
fun SurveyDetailScreen(
    homeViewModel: HomeViewModel = getViewModel(),
    surveyId: String,
    onBackClick: () -> Unit,
) {
    val viewState by homeViewModel.viewState.collectAsStateWithLifecycle()
    val surveyUiModel = viewState.surveys.find { it.id == surveyId }
    var shouldShowStartContent by remember { mutableStateOf(false) }
    var shouldShowSurveyQuestionContent by remember { mutableStateOf(false) }
    var imageScale by remember { mutableStateOf(InitialImageScale) }
    val scope = rememberCoroutineScope()
    // TODO: use property from the ViewModel in the integrate task
    var surveyQuestionUiModels by remember { mutableStateOf<List<SurveyQuestionUiModel>>(emptyList()) }

    // TODO: simulating network latency. Remove in the integrate task.
    LaunchedEffect(Unit) {
        delay(1000)
        // Call API and fetch the questions
        surveyQuestionUiModels = List(5) {
            SurveyQuestionUiModel(
                id = it.plus(1).toString(),
                text = "How fulfilled did you feel during this WFH period?",
                displayType = DisplayType.DROPDOWN,
                imageUrl = surveyUiModel?.largeImageUrl.orEmpty(),
                answerUiModels = List(5) {
                    AnswerUiModel(
                        id = (it + 1).toString(),
                        text = "Text ${it + 1}"
                    )
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        imageScale = FinalImageScale
        shouldShowStartContent = true
    }

    SurveyDetailContent(
        surveyUiModel = surveyUiModel,
        surveyQuestionUiModels = surveyQuestionUiModels,
        shouldShowStartContent = shouldShowStartContent,
        imageScale = imageScale,
        shouldShowSurveyQuestionContent = shouldShowSurveyQuestionContent,
        onBackClick = {
            scope.launch {
                imageScale = InitialImageScale
                shouldShowStartContent = false
                delay(ImageScaleAnimationDurationInMillis.toLong())
                onBackClick()
            }
        },
        onStartSurveyClick = {
            shouldShowSurveyQuestionContent = true
        }
    )
}

@Composable
fun SurveyDetailContent(
    surveyUiModel: SurveyUiModel?,
    surveyQuestionUiModels: List<SurveyQuestionUiModel>,
    shouldShowStartContent: Boolean,
    shouldShowSurveyQuestionContent: Boolean,
    onBackClick: () -> Unit,
    onStartSurveyClick: () -> Unit,
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

    if (shouldShowSurveyQuestionContent) {
        SurveyQuestionContent(
            surveyQuestionUiModels = surveyQuestionUiModels.filter { it.displayType != DisplayType.INTRO },
            onCloseClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(
    name = "Intro",
    showSystemUi = true)
@Composable
fun SurveyDetailScreenStartPagePreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class)
    params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme {
            SurveyDetailContent(
                surveyUiModel = survey,
                surveyQuestionUiModels = survey.surveyQuestionUiModels,
                shouldShowStartContent = true,
                shouldShowSurveyQuestionContent = false,
                onBackClick = {},
                onStartSurveyClick = {},
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
                surveyQuestionUiModels = survey.surveyQuestionUiModels,
                shouldShowStartContent = false,
                shouldShowSurveyQuestionContent = true,
                onBackClick = {},
                onStartSurveyClick = {},
                imageScale = FinalImageScale
            )
        }
    }
}
