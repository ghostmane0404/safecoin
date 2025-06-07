package com.almazbekov.coreui.components.button.rounded

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.coreui.theme.ColorWrapper
import com.almazbekov.ui.contracts.ButtonState

enum class RoundedButtonStyle {
    PRIMARY,
    ;

    private val color: ColorWrapper.BackgroundContentStroke
        @Composable get() = when (this) {
            PRIMARY -> AppTheme.colors.buttonRoundedPrimary
        }

    private val disabledColor: ColorWrapper.BackgroundContentStroke
        @Composable get() = when (this) {
            PRIMARY -> AppTheme.colors.buttonRoundedPrimary
        }

    @Composable
    fun getContentColor(state: ButtonState) = when (state) {
        ButtonState.DISABLED -> disabledColor.content.getColorValue()
        ButtonState.ACTIVE,
        ButtonState.LOADING,
        -> color.content.getColorValue()
    }

    @Composable
    fun getBorderColor(state: ButtonState): Brush = when (state) {
        ButtonState.DISABLED -> disabledColor.stroke.toBrush()
        ButtonState.ACTIVE,
        ButtonState.LOADING,
        -> color.stroke.toBrush()
    }

    private val backgroundColor: Brush
        @Composable get() = color.background.toBrush()

    @Composable
    fun getBackgroundByState(state: ButtonState): Brush = when (state) {
        ButtonState.DISABLED -> disabledColor.background.toBrush()
        else -> backgroundColor
    }
}
