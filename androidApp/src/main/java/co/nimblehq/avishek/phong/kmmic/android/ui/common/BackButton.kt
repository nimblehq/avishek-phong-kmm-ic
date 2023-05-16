package co.nimblehq.avishek.phong.kmmic.android.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R
import co.nimblehq.avishek.phong.kmmic.android.ui.theme.ApplicationTheme

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_arrow_left),
        contentDescription = null,
        colorFilter = ColorFilter.tint(White),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .wrapContentSize()
            .clickable { onClick() }
            .padding(all = 8.dp)
    )
}

@Preview
@Composable
fun BackButtonPreview() {
    ApplicationTheme{
        BackButton(onClick = {})
    }
}
