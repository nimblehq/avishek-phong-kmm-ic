package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.AnswerUiModel
import com.chargemap.compose.numberpicker.ListItemPicker

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    answerUiModels: List<AnswerUiModel>,
) {
    val answerTexts = answerUiModels.map { it.text }
    var answerText by remember { mutableStateOf(answerTexts[0]) }

    ListItemPicker(
        label = { it },
        value = answerText,
        onValueChange = {
            answerText = it
        },
        list = answerTexts,
        dividersColor = White,
        textStyle = MaterialTheme.typography.h6.copy(color = White),
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun SpinnerPreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class)
    params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    ApplicationTheme {
        Spinner(
            answerUiModels = params.survey.surveyQuestionUiModels[0].answerUiModels
        )
    }
}
