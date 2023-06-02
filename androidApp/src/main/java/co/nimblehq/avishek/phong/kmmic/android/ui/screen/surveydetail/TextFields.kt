package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.ui.common.PrimaryTextField
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyAnswerUiModel

@Composable
fun TextFields(
    surveyAnswerUiModels: List<SurveyAnswerUiModel>,
    onAnswersProvided: (answerUiModels: List<SurveyAnswerUiModel>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val answers = remember { surveyAnswerUiModels.toMutableList() }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(surveyAnswerUiModels.size) { index ->
            var value by remember { mutableStateOf("") }

            PrimaryTextField(
                value = value,
                onValueChange = {
                    value = it
                    answers.apply {
                        this[index] = this[index].copy(text = it)
                    }.let(onAnswersProvided)
                },
                placeholder = surveyAnswerUiModels[index].placeholder.orEmpty(),
                imeAction = if (index == surveyAnswerUiModels.lastIndex) ImeAction.Done else ImeAction.Next
            )
        }
    }
}

@Preview
@Composable
fun TextFieldPreview() {
    ApplicationTheme {
        TextFields(
            surveyAnswerUiModels = answerUiModels,
            onAnswersProvided = {}
        )
    }
}
