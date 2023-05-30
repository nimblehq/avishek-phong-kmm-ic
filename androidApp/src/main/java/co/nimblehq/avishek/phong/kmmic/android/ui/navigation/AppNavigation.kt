package co.nimblehq.avishek.phong.kmmic.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.home.HomeScreen
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.splash.SplashScreen
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.surveydetail.SurveyDetailScreen
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.thankyou.ThankYouScreen
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
                    homeViewModel.fetchData()
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
            SurveyDetailScreen(
                homeViewModel = homeViewModel,
                surveyId = navBackStackEntry.arguments?.getString(AppDestination.SurveyDetail.SurveyIdArg).orEmpty(),
                onBackClick = navController::popBackStack,
                onAnswersSubmitted = {
                    navController.navigate(
                        route = AppDestination.ThankYou.route,
                        navOptions = navOptions {
                            popUpTo(route = AppDestination.SurveyDetail.routeWithArgs) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    )
                }
            )
        }

        composable(AppDestination.ThankYou) {
            ThankYouScreen(
                onComplete = navController::popBackStack,
            )
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
