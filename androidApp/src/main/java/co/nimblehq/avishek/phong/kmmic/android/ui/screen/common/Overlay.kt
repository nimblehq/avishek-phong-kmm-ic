package co.nimblehq.avishek.phong.kmmic.android.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun Overlay(
    modifier: Modifier,
    colors: List<Color>
) {
    val gradient = Brush.verticalGradient(colors = colors)
    Box(modifier = modifier.background(gradient))
}
