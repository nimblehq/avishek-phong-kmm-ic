package co.nimblehq.avishek.phong.kmmic.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import co.nimblehq.avishek.phong.kmmic.android.ui.navigation.AppNavHost
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    ApplicationTheme {
        val navController = rememberNavController()
        AppNavHost(
            navController = navController,
            modifier = Modifier.fillMaxSize()
        )
    }
}
