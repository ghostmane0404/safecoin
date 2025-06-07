@file:Suppress("ForbiddenImport")

package com.almazbekov.coreui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.coreui.util.conditional
import com.almazbekov.coreui.util.toBrush
import com.almazbekov.ui.contracts.InputLines
import com.almazbekov.ui.contracts.InputState
import com.almazbekov.ui.contracts.InputStatus

@Suppress("LongParameterList", "LongMethod")
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    inputState: InputState.Simple,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    focusRequester: FocusRequester = remember { FocusRequester() },
    maxChar: Int? = null,
    inputLines: InputLines = InputLines.Single,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
    var hasFocus by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    var inputText by remember(inputState) { mutableStateOf(inputState.value) }

    Column {
        BasicTextField(
            value = inputText,
            onValueChange = {
                if (maxChar == null || it.length <= maxChar) {
                    inputText = it
                    onValueChange.invoke(it)
                }
            },
            modifier = modifier
                .onGloballyPositioned {
                    if (inputState.status is InputStatus.Active && inputState.status.shouldFocus()) {
                        focusRequester.requestFocus()
                    }
                }
                .setHeight(isSingleLineMode = inputLines.isSingleLine())
                .background(
                    color = AppTheme.colors.inputBackground,
                    shape = RectangleShape,
                )
                .textInputBorder(state = inputState.status)
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged {
                    hasFocus = it.isFocused
                    if (it.isFocused) {
                        keyboardController?.show()
                    }
                },
            enabled = inputState.status.isEnabled(),
            readOnly = false,
            textStyle = AppTheme.typography.enlarged.copy(
                textAlign = TextAlign.Start,
                color = if (inputState.status.isEnabled()) {
                    AppTheme.colors.textPrimary
                } else {
                    AppTheme.colors.textTertiary
                },
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = inputLines.isSingleLine(),
            maxLines = when (inputLines) {
                is InputLines.Single -> 1
                is InputLines.Multi -> inputLines.maxLines
            },
            decorationBox = { innerTextField ->
                TextInputDecorationBox(
                    value = inputState.value,
                    placeholder = placeholder,
                    innerTextField = innerTextField,
                    shouldCenter = inputLines.isSingleLine(),
                )
            },
            cursorBrush = SolidColor(AppTheme.colors.cursor),
            interactionSource = interactionSource,
        )
    }
}

internal fun Modifier.setHeight(isSingleLineMode: Boolean): Modifier = composed {
    val singLineHeight = AppTheme.dimens.size16
    val multiLineMinHeight = AppTheme.dimens.size25
    this.conditional(
        condition = isSingleLineMode,
        ifTrue = { height(singLineHeight) },
        ifFalse = { heightIn(min = multiLineMinHeight) },
    )
}

internal fun Modifier.textInputBorder(
    state: InputStatus,
) = composed {
    this.then(
        Modifier.border(
            width = AppTheme.dimens.sizeHalf,
            brush = when (state) {
                InputStatus.Disabled, is InputStatus.Active, is InputStatus.Loading ->
                    AppTheme.colors.transparent.toBrush()
                is InputStatus.Error -> AppTheme.colors.strokeNegative.toBrush()
            },
            shape = RectangleShape,
        ),
    )
}
