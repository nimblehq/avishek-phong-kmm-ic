package co.nimblehq.avishek.phong.kmmic.android.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.avishek.phong.kmmic.android.R

@Composable
fun AlertDialog(
    message: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onConfirmButtonClick: () -> Unit = onDismissRequest,
) {
    AlertDialog(
        text = { Text(text = message) },
        confirmButton = {
            TextButton(
                onClick = { onConfirmButtonClick() }
            ) {
                Text(
                    text = stringResource(id = R.string.ok)
                )
            }
        },
        onDismissRequest = onDismissRequest,
        modifier = modifier.padding(horizontal = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    AlertDialog(
        message = "Message",
        onDismissRequest = {}
    )
}
