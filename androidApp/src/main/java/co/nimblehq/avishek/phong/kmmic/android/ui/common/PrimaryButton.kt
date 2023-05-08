package co.nimblehq.avishek.phong.kmmic.android.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = shapes.medium,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White
        ),
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = modifier.height(56.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            style = typography.button
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "Button Text",
        onClick = {}
    )
}
