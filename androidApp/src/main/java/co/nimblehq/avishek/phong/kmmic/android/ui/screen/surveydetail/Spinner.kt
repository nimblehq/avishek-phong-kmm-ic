package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyAnswerUiModel
import com.chargemap.compose.numberpicker.ListItemPicker

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    surveyAnswerUiModels: List<SurveyAnswerUiModel>,
    onAnswerSelected: (surveyAnswerUiModel: SurveyAnswerUiModel) -> Unit,
) {
    val answerTexts = surveyAnswerUiModels.map { it.text }
    var answerText by remember { mutableStateOf(answerTexts[0]) }

    ListItemPicker(
        label = { it },
        value = answerText,
        onValueChange = { text ->
            answerText = text
            surveyAnswerUiModels.find { it.text == text }?.let(onAnswerSelected)
        },
        list = answerTexts,
        dividersColor = White,
        textStyle = MaterialTheme.typography.h6.copy(color = White),
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun SpinnerPreview() {
    ApplicationTheme {
        Spinner(
            surveyAnswerUiModels = answerUiModels,
            onAnswerSelected = {}
        )
    }
}
