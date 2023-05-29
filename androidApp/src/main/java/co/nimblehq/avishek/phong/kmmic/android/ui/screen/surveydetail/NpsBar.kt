package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyAnswerUiModel

@Composable
fun NpsBar(
    surveyAnswerUiModels: List<SurveyAnswerUiModel>,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .wrapContentWidth()
            .height(94.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .border(
                    border = BorderStroke(1.dp, White),
                    shape = MaterialTheme.shapes.medium,
                )
                .height(57.dp)
        ) {
            items(
                count = surveyAnswerUiModels.size,
                key = { surveyAnswerUiModels[it].id },
            ) { index ->
                val isSelected = index <= selectedIndex
                val answer = surveyAnswerUiModels[index]

                NpsItem(
                    answerUiModel = answer,
                    hasDivider = index < surveyAnswerUiModels.lastIndex,
                    isSelected = isSelected,
                    onClick = {
                        selectedIndex = index
                    },
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }

        Text(
            text = stringResource(id = R.string.nps_not_ikely),
            color = White.copy(alpha = 0.5f),
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.BottomStart)
        )

        Text(
            text = stringResource(id = R.string.nps_likely),
            color = White,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun NpsItem(
    answerUiModel: SurveyAnswerUiModel,
    isSelected: Boolean,
    hasDivider: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        TextButton(
            onClick = onClick,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .width(34.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = answerUiModel.text,
                color = if (isSelected) White else White.copy(alpha = 0.5f),
                style = if (isSelected) MaterialTheme.typography.h6 else MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }

        if (hasDivider) {
            Divider(
                color = White,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(1.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Preview
@Composable
fun NpsBarPreview() {
    ApplicationTheme {
        NpsBar(
            surveyAnswerUiModels = List(10) {
                SurveyAnswerUiModel(
                    id = (it + 1).toString(),
                    text = "${it + 1}",
                    displayOrder = it,
                    placeholder = ""
                )
            }
        )
    }
}
