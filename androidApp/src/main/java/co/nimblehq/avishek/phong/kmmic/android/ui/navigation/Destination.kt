package co.nimblehq.avishek.phong.kmmic.android.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class AppDestination(val route: String = "") {

    open val arguments: List<NamedNavArgument> = emptyList()

    object Splash : AppDestination("splash")

    object Login : AppDestination("login")
}
