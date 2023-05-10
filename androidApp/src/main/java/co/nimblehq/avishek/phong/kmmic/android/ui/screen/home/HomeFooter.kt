package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.extension.placeholder
import co.nimblehq.avishek.phong.kmmic.android.ui.common.NextCircleButton
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFooter(
    pagerState: PagerState,
    pageCount: Int,
    isLoading: Boolean,
    survey: SurveyUiModel?,
    modifier: Modifier = Modifier,
    onSurveyClick: (SurveyUiModel?) -> Unit,
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
                survey = survey,
                onSurveyClick = onSurveyClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeFooterContent(
    pagerState: PagerState,
    pageCount: Int,
    survey: SurveyUiModel?,
    onSurveyClick: (SurveyUiModel?) -> Unit,
) {
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) White else White.copy(alpha = 0.20f)
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)

            )
        }
    }
    Crossfade(targetState = survey?.title) {
        Text(
            text = it.orEmpty(),
            color = White,
            style = typography.h5,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Crossfade(
            targetState = survey?.description,
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
        Spacer(modifier = Modifier.width(8.dp))
        NextCircleButton(
            onClick = { onSurveyClick(survey) }
        )
    }
}

@Composable
private fun HomeFooterPlaceholderContent() {
    Spacer(
        modifier = Modifier
            .size(30.dp, 14.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Spacer(
        modifier = Modifier
            .size(240.dp, 18.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(5.dp))
    Spacer(
        modifier = Modifier
            .size(120.dp, 18.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Spacer(
        modifier = Modifier
            .size(300.dp, 18.dp)
            .placeholder(true)
    )
    Spacer(modifier = Modifier.height(5.dp))
    Spacer(
        modifier = Modifier
            .size(200.dp, 18.dp)
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
                survey = params.surveys[0]
            ) {}
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
