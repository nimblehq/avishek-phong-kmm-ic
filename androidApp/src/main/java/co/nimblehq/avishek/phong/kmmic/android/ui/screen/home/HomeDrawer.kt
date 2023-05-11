package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.Nero90
import coil.compose.AsyncImage

@Composable
fun HomeDrawer(
    userUiModel: UserUiModel?,
    appVersion: String,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(Nero90)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = userUiModel?.name.orEmpty(),
                color = White,
                style = typography.h4
            )
            AsyncImage(
                model = userUiModel?.avatarUrl.orEmpty(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = White.copy(alpha = 0.2f))
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.home_logout),
            color = White.copy(alpha = 0.5f),
            style = typography.body1.copy(fontSize = 20.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLogoutClick() }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = appVersion,
            color = White.copy(alpha = 0.5f),
            style = typography.subtitle1.copy(fontSize = 11.sp)
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun HomeDrawerPreview(
    @PreviewParameter(HomePreviewParameterProvider::class, limit = 1) params: HomePreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme {
            HomeDrawer(
                userUiModel = user,
                appVersion = appVersion,
                onLogoutClick = {}
            )
        }
    }
}
