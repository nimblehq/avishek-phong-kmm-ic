package co.nimblehq.avishek.phong.kmmic.android.extension

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.*

fun Modifier.placeholder(
    isLoading: Boolean,
    shapeValue: Dp = 100.dp,
) = placeholder(
    visible = isLoading,
    color = White.copy(alpha = 0.5f),
    shape = RoundedCornerShape(shapeValue),
    highlight = PlaceholderHighlight.shimmer(
        highlightColor = White.copy(alpha = 0.7f)
    )
)
