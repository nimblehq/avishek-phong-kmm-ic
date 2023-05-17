package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.pager.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.module.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SurveyDetailScreen(
    homeViewModel: HomeViewModel,
    surveyId: String,
    onBackClick: () -> Unit
) {
    val viewState = homeViewModel.viewState.collectAsStateWithLifecycle()
    val surveyUiModel = viewState.value.surveys.find { it.id == surveyId }

    surveyUiModel?.let {
        SurveyStartContent(surveyUiModel = it,
            onStartClick = {
                // TODO: implement in the integrate task
            },
            onBackClick = onBackClick
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
