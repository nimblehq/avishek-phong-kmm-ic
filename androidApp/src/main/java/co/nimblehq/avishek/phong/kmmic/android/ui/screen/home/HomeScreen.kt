package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.extension.pagerFadeTransition
import co.nimblehq.avishek.phong.kmmic.android.ui.common.OverlaidImage
import co.nimblehq.avishek.phong.kmmic.android.ui.common.RtlModalDrawer
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1000)
        isLoading = false
    }

    HomeContentWithDrawer(
        appVersion = "v0.5.0",
        scaffoldState = scaffoldState,
        currentDate = "TUESDAY, MAY 10",
        isLoading = isLoading,
        surveys = listOf(
            SurveyUiModel(
                id = "1",
                title = "Scarlett Bangkok",
                description = "We'd love to hear from you!",
                coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
            ),
            SurveyUiModel(
                id = "2",
                title = "ibis Bangkok Riverside",
                description = "We'd love to hear from you!",
                coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
            ),
            SurveyUiModel(
                id = "3",
                title = "21 on Rajah",
                description = "We'd love to hear from you!",
                coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/0221e768b99dc3576210_"
            )
        ),
        user = UserUiModel(
            email = "avishek@nimblehq.co",
            name = "Avishek",
            avatarUrl = "https://cataas.com/cat/says/hello%20world!"
        )
    )
}

@Composable
private fun HomeContentWithDrawer(
    appVersion: String,
    initialDrawerState: DrawerValue = DrawerValue.Closed,
    scaffoldState: ScaffoldState,
    currentDate: String,
    user: UserUiModel? = null,
    surveys: List<SurveyUiModel>,
    isLoading: Boolean,
) {
    val drawerState = rememberDrawerState(initialDrawerState)
    val scope = rememberCoroutineScope()

    RtlModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            HomeDrawer(
                user = user,
                appVersion = appVersion,
                onLogoutClick = {
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        HomeContent(
            scaffoldState = scaffoldState,
            user = user,
            currentDate = currentDate,
            surveys = surveys,
            isLoading = isLoading,
            onUserAvatarClick = {
                scope.launch { drawerState.open() }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    scaffoldState: ScaffoldState,
    user: UserUiModel?,
    isLoading: Boolean,
    currentDate: String,
    surveys: List<SurveyUiModel>,
    onUserAvatarClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()
    var survey by remember { mutableStateOf<SurveyUiModel?>(null) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            survey = surveys[page]
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HorizontalPager(
                pageCount = surveys.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Box(
                    Modifier
                        .pagerFadeTransition(page, pagerState)
                        .fillMaxSize()
                ) {
                    OverlaidImage(
                        imageUrl = surveys[page].coverImageUrl
                    )
                }
            }

            HomeHeader(
                isLoading = isLoading,
                dateTime = currentDate,
                user = user,
                onUserAvatarClick = onUserAvatarClick,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 8.dp)
            )

            HomeFooter(
                pagerState = pagerState,
                pageCount = surveys.size,
                isLoading = isLoading,
                survey = survey,
                modifier = Modifier
                    .navigationBarsPadding()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 54.dp),
                onSurveyClick = {
                    // TODO: implement in the integrate task
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(HomePreviewParameterProvider::class) params: HomePreviewParameterProvider.Params,
) {
    with(params) {
        ApplicationTheme {
            HomeContentWithDrawer(
                appVersion = appVersion,
                initialDrawerState = drawerState,
                scaffoldState = rememberScaffoldState(),
                currentDate = currentDate,
                user = user,
                surveys = surveys,
                isLoading = isLoading
            )
        }
    }
}
