package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyAnswerUiModel

@Composable
fun MultiChoiceForm(
    surveyAnswerUiModels: List<SurveyAnswerUiModel>,
    modifier: Modifier = Modifier,
) {
    var checkedAnswers by remember { mutableStateOf(emptySet<SurveyAnswerUiModel>()) }
    LazyColumn(modifier = modifier.padding(horizontal = 80.dp)) {
        items(
            count = surveyAnswerUiModels.size,
            key = { surveyAnswerUiModels[it].id }
        ) { index ->
            val answerUiModel = surveyAnswerUiModels[index]
            val isChecked = checkedAnswers.contains(answerUiModel)

            Choice(
                answerUiModel = answerUiModel,
                isChecked = isChecked,
                onClick = {
                    checkedAnswers = if (checkedAnswers.contains(answerUiModel)) {
                        checkedAnswers.minus(answerUiModel)
                    } else {
                        checkedAnswers.plus(answerUiModel)
                    }
                }
            )

            if (index < surveyAnswerUiModels.lastIndex) {
                Divider(color = White, thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun Choice(
    answerUiModel: SurveyAnswerUiModel,
    isChecked: Boolean,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick)
    ) {
        Text(
            text = answerUiModel.text.orEmpty(),
            color = if (isChecked) White else White.copy(alpha = 0.5f),
            style = if (isChecked) {
                MaterialTheme.typography.h6
            } else {
                MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal)
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 14.dp)
        )

        Image(
            painter = painterResource(
                if (isChecked) {
                    R.drawable.ic_checkbox_checked
                } else {
                    R.drawable.ic_checkbox_unchecked
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
fun MultipleChoiceFormPreview() {
    ApplicationTheme {
        MultiChoiceForm(surveyAnswerUiModels = answerUiModels)
    }
}
