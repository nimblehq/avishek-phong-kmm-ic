package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
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
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyHeaderUiModel
import coil.compose.AsyncImage

@Composable
fun HomeDrawer(
    surveyHeaderUiModel: SurveyHeaderUiModel?,
    appVersion: String,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxSize()
            .background(Nero90)
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = surveyHeaderUiModel?.name.orEmpty(),
                color = White,
                style = typography.h4
            )
            AsyncImage(
                model = surveyHeaderUiModel?.imageUrl.orEmpty(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
        }

        Divider(
            color = White.copy(alpha = 0.2f),
            modifier = Modifier.padding(top = 20.dp)
        )

        TextButton(onClick = onLogoutClick) {
            Text(
                text = stringResource(id = R.string.home_logout),
                color = White.copy(alpha = 0.5f),
                style = typography.body1.copy(fontSize = 20.sp),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = appVersion,
            color = White.copy(alpha = 0.5f),
            style = typography.subtitle1.copy(fontSize = 11.sp),
            modifier = Modifier.padding(bottom = 40.dp)
        )
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
                surveyHeaderUiModel = surveyHeader,
                appVersion = appVersion,
                onLogoutClick = {}
            )
        }
    }
}
