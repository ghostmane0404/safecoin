package com.almazbekov.coreui.theme

import androidx.compose.ui.graphics.Color

data class AppColors(
    val backgroundPrimary: Color = Palette.black,
    val textPrimary: Color = Palette.white,
    val textSecondary: Color = Palette.white.copy(alpha = 0.6f),
    val textTertiary: Color = Palette.white.copy(alpha = 0.3f),
    val textInverted: Color = Palette.grey200,
    val textPositive: Color = Palette.green200,
    val textNegative: Color = Palette.red100,
    val indicatorPrimary: Color = Palette.white,
    val indicatorSecondary: Color = Palette.grey100.copy(alpha = 0.3f),
    val buttonPrimary: ColorWrapper.BackgroundContentStroke = ColorWrapper.BackgroundContentStroke(
        background = ColorWrapper.Simple(color = Palette.white),
        content = ColorWrapper.Simple(color = Palette.black),
        stroke = ColorWrapper.Simple(color = Color.Transparent),
    ),
    val buttonSecondary: ColorWrapper.BackgroundContentStroke = ColorWrapper.BackgroundContentStroke(
        background = ColorWrapper.Simple(color = Palette.black),
        content = ColorWrapper.Simple(color = Palette.white),
        stroke = ColorWrapper.Simple(color = Palette.white),
    ),
    val buttonRoundedPrimary: ColorWrapper.BackgroundContentStroke = ColorWrapper.BackgroundContentStroke(
        background = ColorWrapper.Simple(color = Palette.purple200),
        content = ColorWrapper.Simple(color = Palette.white),
        stroke = ColorWrapper.Simple(color = Color.Transparent),
    ),
    val iconPrimary: Color = Palette.white,
    val iconSecondary: Color = Palette.white.copy(alpha = 0.3f),
    val iconPositive: Color = Palette.green100,
    val iconNegative: Color = Palette.red100,
    val inputBackground: Color = Color.Transparent,
    val transparent: Color = Color.Transparent,
    val strokeNegative: Color = Palette.red100,
    val cursor: Color = Palette.purple200,
)
