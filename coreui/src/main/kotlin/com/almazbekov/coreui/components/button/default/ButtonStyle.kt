package com.almazbekov.coreui.components.button.default

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.coreui.theme.ColorWrapper
import com.almazbekov.ui.contracts.ButtonState

enum class ButtonStyle {
    PRIMARY,
    SECONDARY,
    ;

    private val color: ColorWrapper.BackgroundContentStroke
        @Composable get() = when (this) {
            PRIMARY -> AppTheme.colors.buttonPrimary
            SECONDARY -> AppTheme.colors.buttonSecondary
        }

    private val disabledColor: ColorWrapper.BackgroundContentStroke
        @Composable get() = when (this) {
            PRIMARY -> AppTheme.colors.buttonPrimary
            SECONDARY -> AppTheme.colors.buttonSecondary
        }

    @Composable
    fun getBackground(state: ButtonState): Brush = when (state) {
        ButtonState.DISABLED -> disabledColor.background.toBrush()
        ButtonState.ACTIVE,
        ButtonState.LOADING,
        -> color.background.toBrush()
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
}
