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
import coil.compose.AsyncImage

@Composable
fun HomeHeader(
    isLoading: Boolean,
    dateTime: String,
    userUiModel: UserUiModel?,
    onUserAvatarClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = dateTime.uppercase(),
            color = White,
            style = typography.subtitle1,
            modifier = Modifier.placeholder(isLoading = isLoading)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.home_today),
                color = White,
                style = typography.h4,
                modifier = Modifier.placeholder(isLoading = isLoading)
            )
            AsyncImage(
                model = userUiModel?.avatarUrl.orEmpty(),
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
                dateTime = currentDate,
                userUiModel = user,
                onUserAvatarClick = {}
            )
        }
    }
}
