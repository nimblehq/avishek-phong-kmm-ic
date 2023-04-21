package co.nimblehq.avishek.phong.kmmic.android.ui.screen.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import co.nimblehq.avishek.phong.kmmic.android.R

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Image(
        painterResource(id = R.drawable.logo_white),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
