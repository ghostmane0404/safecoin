package com.almazbekov.coreui.components.button.default

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.ui.contracts.ButtonState

@Suppress("LongParameterList")
@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int,
    buttonState: ButtonState,
    buttonStyle: ButtonStyle = ButtonStyle.PRIMARY,
    onClick: () -> Unit,
) = DefaultButton(
    modifier = modifier,
    text = stringResource(textRes),
    buttonStyle = buttonStyle,
    buttonState = buttonState,
    onClick = onClick,
)

@Suppress("LongParameterList")
@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonState: ButtonState,
    buttonStyle: ButtonStyle = ButtonStyle.PRIMARY,
    onClick: () -> Unit,
) {
    val height = AppTheme.dimens.size14
    val shape = RectangleShape

    Button(
        onClick = onClick,
        modifier = modifier
            .height(height),
        shape = shape,
        border = BorderStroke(
            width = AppTheme.dimens.buttonStroke,
            brush = buttonStyle.getBorderColor(state = buttonState),
        ),
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        ),
        enabled = buttonState.isActive(),
        contentPadding = PaddingValues(),
        elevation = when (buttonStyle) {
            ButtonStyle.SECONDARY -> null

            else -> ButtonDefaults.buttonElevation()
        },
    ) {
        ButtonContent(
            text = text,
            shape = shape,
            buttonStyle = buttonStyle,
            buttonState = buttonState,
            contentPadding = PaddingValues(),
            buttonHeight = height,
        )
    }
}

@Suppress("LongParameterList")
@Composable
private fun ButtonContent(
    text: String,
    shape: Shape,
    buttonStyle: ButtonStyle,
    buttonState: ButtonState,
    contentPadding: PaddingValues,
    buttonHeight: Dp,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(
                brush = buttonStyle.getBackground(state = buttonState),
                shape = shape,
            )
            .fillMaxWidth()
            .padding(contentPadding),
        contentAlignment = Alignment.Center,
    ) {
        if (buttonState.isLoading()) {
            CircularProgressIndicator(
                modifier = Modifier.size(buttonHeight),
                color = buttonStyle.getContentColor(state = buttonState),
                strokeWidth = AppTheme.dimens.sizeHalf,
            )
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    color = buttonStyle.getContentColor(state = buttonState),
                    text = text.uppercase(),
                    style = AppTheme.typography.button,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
