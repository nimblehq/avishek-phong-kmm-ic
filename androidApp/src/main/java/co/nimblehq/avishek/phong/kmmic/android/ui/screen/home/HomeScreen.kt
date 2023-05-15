package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.extension.pagerFadeTransition
import co.nimblehq.avishek.phong.kmmic.android.ui.common.*
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.BlackRussian
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TopGradientAlpha: Float = 0.01f
private const val BottomGradientAlpha: Float = 0.6f

@Composable
fun HomeScreen() {
    var isLoading by remember { mutableStateOf(true) }
    var userUiModel by remember { mutableStateOf<UserUiModel?>(null) }
    var surveyUiModels by remember { mutableStateOf(emptyList<SurveyUiModel>()) }
    var currentDate by remember { mutableStateOf("") }
    var appVersion by remember { mutableStateOf("") }

    // TODO: replace this when integrating the Home screen with the API data
    LaunchedEffect(Unit) {
        delay(1000)
        isLoading = false
        userUiModel = UserUiModel(
            email = "avishek@nimblehq.co",
            name = "Avishek",
            avatarUrl = "https://cataas.com/cat/says/hello%20world!"
        )
        surveyUiModels = listOf(
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
        )
        currentDate = "TUESDAY, MAY 10"
        appVersion = "v0.5.0"
    }

    HomeContentWithDrawer(
        appVersion = appVersion,
        currentDate = currentDate,
        isLoading = isLoading,
        surveyUiModels = surveyUiModels,
        userUiModel = userUiModel
    )
}

@Composable
private fun HomeContentWithDrawer(
    appVersion: String,
    initialDrawerState: DrawerValue = DrawerValue.Closed,
    currentDate: String,
    userUiModel: UserUiModel? = null,
    surveyUiModels: List<SurveyUiModel>,
    isLoading: Boolean,
) {
    val drawerState = rememberDrawerState(initialDrawerState)
    val scope = rememberCoroutineScope()

    RtlModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            HomeDrawer(
                userUiModel = userUiModel,
                appVersion = appVersion,
                onLogoutClick = {
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        HomeContent(
            userUiModel = userUiModel,
            currentDate = currentDate,
            surveyUiModels = surveyUiModels,
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
    userUiModel: UserUiModel?,
    isLoading: Boolean,
    currentDate: String,
    surveyUiModels: List<SurveyUiModel>,
    onUserAvatarClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState()
    var surveyUiModel by remember { mutableStateOf<SurveyUiModel?>(null) }

    LaunchedEffect(surveyUiModels) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            surveyUiModel = surveyUiModels.getOrNull(page)
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
                pageCount = surveyUiModels.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BlackRussian)
            ) { page ->
                Box(
                    Modifier
                        .pagerFadeTransition(page, pagerState)
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = surveyUiModels.getOrNull(page)?.coverImageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = TopGradientAlpha),
                                        Color.Black.copy(alpha = BottomGradientAlpha)
                                    )
                                )
                            )
                    )
                }
            }

            HomeHeader(
                isLoading = isLoading,
                dateTime = currentDate,
                userUiModel = userUiModel,
                onUserAvatarClick = onUserAvatarClick,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 8.dp)
            )

            HomeFooter(
                pagerState = pagerState,
                pageCount = surveyUiModels.size,
                isLoading = isLoading,
                surveyUiModel = surveyUiModel,
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
                currentDate = currentDate,
                userUiModel = user,
                surveyUiModels = surveys,
                isLoading = isLoading
            )
        }
    }
}
