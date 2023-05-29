package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.ui.common.PrimaryTextField
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType.TEXTFIELD
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyAnswerUiModel

@Composable
fun TextFields(
    surveyAnswerUiModels: List<SurveyAnswerUiModel>,
    modifier: Modifier = Modifier,
) {
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
        TextFields(surveyAnswerUiModels = answerUiModels)
    }
}
