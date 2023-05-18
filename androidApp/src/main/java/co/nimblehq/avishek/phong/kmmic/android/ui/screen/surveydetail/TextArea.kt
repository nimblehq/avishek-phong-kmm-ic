package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import co.nimblehq.avishek.phong.kmmic.android.ui.common.PrimaryTextField
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyAnswerUiModel

@Composable
fun TextArea(
    surveyAnswerUiModel: SurveyAnswerUiModel,
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf("") }

    PrimaryTextField(
        value = value,
        placeholder = surveyAnswerUiModel.placeholder.orEmpty(),
        singleLine = false,
        imeAction = ImeAction.Done,
        onValueChange = { value = it },
        modifier = modifier
    )
}

@Preview
@Composable
fun TextAreaPreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class) params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    ApplicationTheme {
        TextArea(
            surveyAnswerUiModel = params.survey.questionUiModels[0].answers[0]
        )
    }
}
