package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.extension.placeholder
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyHeaderUiModel
import coil.compose.AsyncImage

@Composable
fun HomeHeader(
    isLoading: Boolean,
    surveyHeaderUiModel: SurveyHeaderUiModel?,
    onUserAvatarClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = surveyHeaderUiModel?.dateText.orEmpty().uppercase(),
            color = White,
            style = typography.subtitle1,
            modifier = Modifier
                .padding(top = 28.dp)
                .placeholder(isLoading = isLoading)
        )
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.home_today),
                color = White,
                style = typography.h4,
                modifier = Modifier.placeholder(isLoading = isLoading)
            )
            AsyncImage(
                model = surveyHeaderUiModel?.imageUrl.orEmpty(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .placeholder(isLoading = isLoading)
                    .clickable { onUserAvatarClick() }
            )
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview(
    @PreviewParameter(HomePreviewParameterProvider::class, limit = 3) params: HomePreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme() {
            HomeHeader(
                isLoading = isLoading,
                surveyHeaderUiModel = surveyHeader,
                onUserAvatarClick = {}
            )
        }
    }
}
