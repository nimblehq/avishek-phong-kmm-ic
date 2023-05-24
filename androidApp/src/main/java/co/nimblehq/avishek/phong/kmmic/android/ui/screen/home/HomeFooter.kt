package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.extension.placeholder
import co.nimblehq.avishek.phong.kmmic.android.ui.common.HorizontalPagerIndicator
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFooter(
    pagerState: PagerState,
    pageCount: Int,
    isLoading: Boolean,
    surveyUiModel: SurveyUiModel?,
    onSurveyClick: (SurveyUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        if (isLoading) {
            HomeFooterPlaceholderContent()
        } else {
            HomeFooterContent(
                pagerState = pagerState,
                pageCount = pageCount,
                surveyUiModel = surveyUiModel,
                onSurveyClick = {
                    it?.let(onSurveyClick::invoke)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeFooterContent(
    pagerState: PagerState,
    pageCount: Int,
    surveyUiModel: SurveyUiModel?,
    onSurveyClick: (SurveyUiModel?) -> Unit,
) {
    HorizontalPagerIndicator(
        pagerState = pagerState,
        pageCount = pageCount,
        activeColor = White,
        inactiveColor = White.copy(alpha = 0.20f),
        spacing = 5.dp
    )

    Crossfade(
        targetState = surveyUiModel?.title,
        modifier = Modifier.padding(top = 26.dp)
    ) {
        Text(
            text = it.orEmpty(),
            color = White,
            style = typography.h5,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(top = 2.dp)
    ) {
        Crossfade(
            targetState = surveyUiModel?.description,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = it.orEmpty(),
                color = White.copy(alpha = 0.70f),
                style = typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        FloatingActionButton(
            backgroundColor = White,
            onClick = { onSurveyClick(surveyUiModel) },
            modifier = Modifier
                .padding(start = 8.dp)
                .size(56.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun HomeFooterPlaceholderContent() {
    Spacer(
        modifier = Modifier
            .size(30.dp, 14.dp)
            .placeholder(true)
    )

    Spacer(
        modifier = Modifier
            .size(240.dp, 28.dp)
            .padding(top = 10.dp)
            .placeholder(true)
    )

    Spacer(
        modifier = Modifier
            .size(120.dp, 23.dp)
            .padding(top = 5.dp)
            .placeholder(true)
    )

    Spacer(
        modifier = Modifier
            .size(300.dp, 28.dp)
            .padding(top = 10.dp)
            .placeholder(true)
    )

    Spacer(
        modifier = Modifier
            .size(200.dp, 23.dp)
            .padding(top = 5.dp)
            .placeholder(true)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun HomeFooterPreview(
    @PreviewParameter(HomePreviewParameterProvider::class) params: HomePreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme {
            HomeFooter(
                pagerState = rememberPagerState(),
                pageCount = 3,
                isLoading = isLoading,
                surveyUiModel = params.surveys[0],
                onSurveyClick = {}
            )
        }
    }
}

@Preview
@Composable
fun HomeFooterPlaceholderContentPreview() {
    ApplicationTheme {
        HomeFooterPlaceholderContent()
    }
}
