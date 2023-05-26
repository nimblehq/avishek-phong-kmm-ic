package co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType
import co.nimblehq.avishek.phong.kmmic.domain.model.QuestionDisplayType.*

private const val SelectedEmojiAlpha = 1f
private const val UnselectedEmojiAlpha = 0.5f
private const val StarEmoji = "⭐️"
private const val HeartEmoji = "❤️"
private const val AngryEmoji = "\uD83D\uDE21"
private const val ConfusedEmoji = "\uD83D\uDE15"
private const val StraightFaceEmoji = "\uD83D\uDE10"
private const val SmileEmoji = "\uD83D\uDE42"
private const val GrinningEmoji = "\uD83D\uDE04"

@Composable
fun RatingBar(
    emojis: List<String>,
    modifier: Modifier = Modifier,
    isRangeSelectable: Boolean = false,
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(emojis.size) { index ->
            val isSelected = if (isRangeSelectable) {
                index <= selectedIndex
            } else {
                index == selectedIndex
            }
            val alpha = if (isSelected) SelectedEmojiAlpha else UnselectedEmojiAlpha
            TextButton(
                onClick = { selectedIndex = index },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(34.dp)
            ) {
                Text(
                    text = emojis[index],
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.alpha(alpha)
                )
            }
        }
    }
}

fun QuestionDisplayType.toEmojis(count: Int): List<String> {
    return when (this) {
        STAR -> List(count) { StarEmoji }
        HEART -> List(count) { HeartEmoji }
        SMILEY -> listOf(AngryEmoji, ConfusedEmoji, StraightFaceEmoji, SmileEmoji, GrinningEmoji)
        else -> emptyList()
    }
}

@Preview
@Composable
fun RatingBarPreview(
    @PreviewParameter(SurveyDetailScreenPreviewParameterProvider::class)
    params: SurveyDetailScreenPreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme {
            RatingBar(
                emojis = survey
                    .questionUiModels[0]
                    .displayType
                    .toEmojis(survey.questionUiModels[0].answers.size)
            )
        }
    }
}
