package co.nimblehq.avishek.phong.kmmic.android.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val TextFieldHeight = 56.dp
private const val PlaceholderColorAlpha = 0.3f

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    contentDescription: String = "",
    singleLine: Boolean = true,
    isError: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.body1
            )
        },
        singleLine = singleLine,
        colors = appTextFieldColors(),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        trailingIcon = trailingIcon,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = TextFieldHeight)
            .clip(shapes.large)
            .semantics { this.contentDescription = contentDescription }
            .border(
                width = 1.dp,
                color = if (isError) Color.Red else Color.Transparent,
                shape = shapes.large
            )
    )
}

@Composable
internal fun appTextFieldColors(
    textColor: Color = Color.White,
    cursorColor: Color = Color.White,
    unfocusedIndicatorColor: Color = Color.Transparent,
    focusedIndicatorColor: Color = Color.Transparent,
    disabledIndicatorColor: Color = Color.Transparent,
    placeholderColor: Color = MaterialTheme.colors.onSurface.copy(alpha = PlaceholderColorAlpha),
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    cursorColor = cursorColor,
    unfocusedIndicatorColor = unfocusedIndicatorColor,
    focusedIndicatorColor = focusedIndicatorColor,
    disabledIndicatorColor = disabledIndicatorColor,
    placeholderColor = placeholderColor
)

@Preview
@Composable
fun PrimaryTextFieldPreview() {
    PrimaryTextField(
        value = "",
        placeholder = "Email",
        onValueChange = {}
    )
}

@Preview
@Composable
fun PrimaryTextFieldWithErrorPreview() {
    PrimaryTextField(
        value = "",
        placeholder = "Email",
        onValueChange = {},
        isError = true
    )
}
