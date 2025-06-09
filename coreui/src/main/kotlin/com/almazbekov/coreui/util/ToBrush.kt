package com.almazbekov.coreui.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Color.toBrush(): Brush = Brush.linearGradient(listOf(this, this))
