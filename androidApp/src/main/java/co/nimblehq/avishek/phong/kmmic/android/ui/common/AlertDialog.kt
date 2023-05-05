package co.nimblehq.avishek.phong.kmmic.android.ui.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.nimblehq.avishek.phong.kmmic.android.R

@Composable
fun AlertDialog(
    message: String,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit = onDismissRequest,
) {
    AlertDialog(
        text = { Text(text = message) },
        confirmButton = {
            Button(
                onClick = { onConfirmButtonClick() }
            ) {
                Text(
                    text = stringResource(id = R.string.ok)
                )
            }
        },
        onDismissRequest = onDismissRequest
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
