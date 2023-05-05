package co.nimblehq.avishek.phong.kmmic.android.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    HomeContent(modifier = Modifier.fillMaxSize())
}

@Composable
fun HomeContent(modifier: Modifier) {
    Text(
        text = "Home under construction",
        style = MaterialTheme.typography.h1,
        modifier = modifier
    )
}
