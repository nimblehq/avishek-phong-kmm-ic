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
import org.koin.androidx.compose.getViewModel

@Composable
fun SurveyDetailScreen(
    homeViewModel: HomeViewModel = getViewModel(),
    surveyId: String,
    onBackClick: () -> Unit,
) {
    val viewState by homeViewModel.viewState.collectAsStateWithLifecycle()
    val surveyUiModel = viewState.surveys.find { it.id == surveyId }
    var surveyQuestionUiModels by remember { mutableStateOf<List<SurveyQuestionUiModel>>(emptyList()) }

    surveyUiModel?.let {
        SurveyStartContent(
            surveyUiModel = it,
            onStartClick = {
                // TODO: implement in the integrate task
                // Call API and fetch the questions
                surveyQuestionUiModels = List(5) {
                    SurveyQuestionUiModel(
                        id = it.plus(1).toString(),
                        text = "How fulfilled did you feel during this WFH period?",
                        displayType = DisplayType.DROPDOWN,
                        imageUrl = surveyUiModel.largeImageUrl,
                        answerUiModels = List(5) {
                            AnswerUiModel(
                                id = (it + 1).toString(),
                                text = "Text ${it + 1}"
                            )
                        }
                    )
                }
            },
            onBackClick = onBackClick
        )
    }

    if (surveyQuestionUiModels.isNotEmpty()) {
        SurveyQuestionContent(
            surveyQuestionUiModels = surveyQuestionUiModels.filter { it.displayType != DisplayType.INTRO },
            onCloseClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SurveyDetailScreenPreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class)
    params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme() {
            SurveyDetailScreen(
                homeViewModel = getViewModel(),
                surveyId = survey.id,
                onBackClick = {}
            )
        }
    }
}
