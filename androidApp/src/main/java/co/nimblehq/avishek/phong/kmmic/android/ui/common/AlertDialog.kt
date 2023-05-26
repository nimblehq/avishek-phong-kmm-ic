package co.nimblehq.avishek.phong.kmmic.android.ui.common

import androidx.compose.foundation.layout.*
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

@Composable
fun AlertDialog(
    title: String,
    message: String,
    onYesClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6
            )
        },
        text = { Text(text = message) },
        buttons = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                TextButton(
                    onClick = onCancelClick
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel)
                    )
                }
                TextButton(
                    onClick = onYesClick
                ) {
                    Text(
                        text = stringResource(id = R.string.yes)
                    )
                }
            }
        },
        onDismissRequest = {},
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

@Preview(showBackground = true)
@Composable
fun AlertDialogMultiButtonsPreview() {
    AlertDialog(
        title = "Title",
        message = "Message",
        onYesClick = {},
        onCancelClick = {}
    )
}
