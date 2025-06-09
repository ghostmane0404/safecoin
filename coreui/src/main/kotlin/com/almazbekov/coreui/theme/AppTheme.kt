package com.almazbekov.coreui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

object AppTheme {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    operator fun invoke(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
        CompositionLocalProvider(
            LocalAppTypographyComposition provides LocalAppTypographyComposition.current,
            LocalAppDimensComposition provides LocalAppDimensComposition.current,
            LocalAppColorsComposition provides LocalAppColorsComposition.current,
            content = content,
        )
    }

    private val LocalAppDimensComposition = staticCompositionLocalOf {
        AppDimens()
    }

    val dimens: AppDimens
        @Composable
        get() = LocalAppDimensComposition.current

    private val LocalAppColorsComposition = staticCompositionLocalOf {
        AppColors()
    }

    val colors: AppColors
        @Composable
        get() = LocalAppColorsComposition.current

    private val LocalAppTypographyComposition = staticCompositionLocalOf {
        AppTypography()
    }

    val typography: AppTypography
        @Composable
        get() = LocalAppTypographyComposition.current
}
