package co.nimblehq.avishek.phong.kmmic.android.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private const val TopGradientAlpha: Float = 0.01f
private const val BottomGradientAlpha: Float = 0.6f

@Composable
fun OverlaidImage(
    @DrawableRes imageRes: Int? = null,
    imageUrl: String? = null
) {
    Box(modifier = Modifier.fillMaxSize()) {
        imageRes?.let {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .blur(radius = 0.dp)
            )
        }
        imageUrl?.let {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .blur(radius = 0.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Black.copy(alpha = TopGradientAlpha),
                            Black.copy(alpha = BottomGradientAlpha)
                        )
                    )
                )
        )
    }
}
