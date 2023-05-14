package co.nimblehq.avishek.phong.kmmic.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.home.HomeScreen
import co.nimblehq.avishek.phong.kmmic.android.ui.screen.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
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
            HomeScreen()
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
