package com.almazbekov.coreui.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@SuppressLint("UnnecessaryComposedModifier")
inline fun Modifier.thenIf(
    condition: Boolean,
    crossinline other: @Composable Modifier.() -> Modifier,
) = composed { if (condition) other() else this }
