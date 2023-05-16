package co.nimblehq.avishek.phong.kmmic.android.ui.navigation

import androidx.navigation.*

sealed class AppDestination(val route: String = "") {

    open val arguments: List<NamedNavArgument> = emptyList()

    object Splash : AppDestination("splash")

    object Home : AppDestination("home")
    object SurveyDetail : AppDestination("survey-detail") {

        const val surveyId = "surveyId"
        val routeWithArgs = "$route/{$surveyId}"

        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(surveyId) { type = NavType.StringType }
            )
    }
}
