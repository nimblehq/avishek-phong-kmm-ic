package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.nimblehq.avishek.phong.kmmic.android.extension.pagerFadeTransition
import co.nimblehq.avishek.phong.kmmic.android.ui.common.*
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.BlackRussian
import co.nimblehq.avishek.phong.kmmic.presentation.module.HomeViewModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyHeaderUiModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

private const val TopGradientAlpha: Float = 0.01f
private const val BottomGradientAlpha: Float = 0.6f

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = getViewModel(),
    onSurveyClick: (SurveyUiModel) -> Unit,
) {
    val viewState by homeViewModel.viewState.collectAsStateWithLifecycle()
    val appVersion by homeViewModel.appVersion.collectAsStateWithLifecycle()

    HomeContentWithDrawer(
        appVersion = appVersion,
        isLoading = viewState.isLoading,
        surveyUiModels = viewState.surveys,
        surveyHeaderUiModel = viewState.headerUiModel,
        onRefresh = {
            homeViewModel.refresh()
        },
        onPageChange = {
            homeViewModel.fetchMoreSurveysIfNeeded(it)
        },
        onSurveyClick = onSurveyClick
    )
}

@Composable
private fun HomeContentWithDrawer(
    appVersion: String,
    initialDrawerState: DrawerValue = DrawerValue.Closed,
    surveyHeaderUiModel: SurveyHeaderUiModel? = null,
    surveyUiModels: List<SurveyUiModel>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onSurveyClick: (SurveyUiModel) -> Unit,
    onPageChange: (page: Int) -> Unit,
) {
    val drawerState = rememberDrawerState(initialDrawerState)
    val scope = rememberCoroutineScope()

    RtlModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            HomeDrawer(
                surveyHeaderUiModel = surveyHeaderUiModel,
                appVersion = appVersion,
                onLogoutClick = {
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        HomeContent(
            surveyHeaderUiModel = surveyHeaderUiModel,
            surveyUiModels = surveyUiModels,
            isLoading = isLoading,
            onRefresh = onRefresh,
            onPageChange = onPageChange,
            onUserAvatarClick = {
                scope.launch { drawerState.open() }
            },
            onSurveyClick = onSurveyClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    surveyHeaderUiModel: SurveyHeaderUiModel?,
    isLoading: Boolean,
    surveyUiModels: List<SurveyUiModel>,
    onUserAvatarClick: () -> Unit,
    onSurveyClick: (surveyUiModel: SurveyUiModel) -> Unit,
    onRefresh: () -> Unit,
    onPageChange: (page: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isFirstLaunch by remember { mutableStateOf(true) }
    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val refreshingState = rememberPullRefreshState(
        refreshing = if(isFirstLaunch) false else isLoading,
        onRefresh = {
            onRefresh()
            scope.launch {
                pagerState.scrollToPage(0)
            }
        }
    )
    var surveyUiModel by remember { mutableStateOf<SurveyUiModel?>(null) }
    if (isFirstLaunch) {
        isFirstLaunch = false
    }

    LaunchedEffect(surveyUiModels) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            surveyUiModel = surveyUiModels.getOrNull(page)
            onPageChange(page)
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
                .pullRefresh(refreshingState)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Box(modifier = Modifier.fillParentMaxHeight()) {
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
                                    model = surveyUiModels.getOrNull(page)?.largeImageUrl,
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
                            surveyHeaderUiModel = surveyHeaderUiModel,
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
                                onSurveyClick(it)
                            }
                        )
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = isLoading,
                state = refreshingState,
                modifier = Modifier.align(alignment = Alignment.TopCenter)
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
                surveyHeaderUiModel = surveyHeader,
                surveyUiModels = surveys,
                isLoading = isLoading,
                onRefresh = {},
                onPageChange = {},
                onSurveyClick = {}
            )
        }
    }
}
