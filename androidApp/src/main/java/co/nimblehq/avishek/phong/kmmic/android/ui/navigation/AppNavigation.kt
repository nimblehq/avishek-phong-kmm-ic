package co.nimblehq.avishek.phong.kmmic.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.home.HomeScreen
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.splash.SplashScreen
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail.SurveyDetailScreen
import co.nimblehq.avishek.phong.kmmic.presentation.module.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val homeViewModel: HomeViewModel = getViewModel()
    NavHost(
        navController = navController,
        startDestination = AppDestination.Splash.route,
        modifier = modifier
    ) {
        composable(AppDestination.Splash) {
            SplashScreen(
                onLoginSuccess = {
                    navController.navigate(
                        route = AppDestination.Home.route,
                        navOptions = navOptions {
                            popUpTo(route = AppDestination.Splash.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    )
                }
            )
        }

        composable(AppDestination.Home) {
            HomeScreen(homeViewModel = homeViewModel) { surveyUiModel ->
                navController.navigate(
                    route = "${AppDestination.SurveyDetail.route}/${surveyUiModel.id}"
                )
            }
        }

        composable(
            route = AppDestination.SurveyDetail.routeWithArgs,
            arguments = AppDestination.SurveyDetail.arguments
        ) { navBackStackEntry ->
            val surveyId = navBackStackEntry.arguments?.getString(AppDestination.SurveyDetail.surveyId)
            surveyId?.let {
                SurveyDetailScreen(
                    homeViewModel = homeViewModel,
                    surveyId = it,
                    onBackClick = navController::popBackStack
                )
            }
        }
    }
}

private fun NavGraphBuilder.composable(
    destination: AppDestination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = deepLinks,
        content = content
    )
}
