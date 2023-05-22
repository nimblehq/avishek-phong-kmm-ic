package co.nimblehq.avishek.phong.kmmic.android.ui.navigation

import androidx.navigation.*

sealed class AppDestination(val route: String = "") {

    open val arguments: List<NamedNavArgument> = emptyList()

    object Splash : AppDestination("splash")

    object Home : AppDestination("home")
    object SurveyDetail : AppDestination("survey-detail") {

        const val SurveyIdArg = "surveyId"
        val routeWithArgs = "$route/{$SurveyIdArg}"

        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(SurveyIdArg) { type = NavType.StringType }
            )
    }
}
