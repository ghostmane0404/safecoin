package com.almazbekov.coreui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

sealed interface ColorWrapper {
    fun toBrush(): Brush

    fun getColorValue(): Color

    sealed interface SingleColorWrapper : ColorWrapper

    data class Simple(val color: Color) : SingleColorWrapper {
        override fun toBrush(): Brush = SolidColor(color)
        override fun getColorValue() = color
    }

    data class Gradient(val colors: List<Color>, val angle: Angle = Angle.CW0) : SingleColorWrapper {
        private val gradientOffset: GradientOffset = when (angle) {
            Angle.CW45 -> GradientOffset(
                start = Offset.Zero,
                end = Offset.Infinite,
            )
            Angle.CW90 -> GradientOffset(
                start = Offset.Zero,
                end = Offset(0f, Float.POSITIVE_INFINITY),
            )
            Angle.CW135 -> GradientOffset(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset(0f, Float.POSITIVE_INFINITY),
            )
            Angle.CW180 -> GradientOffset(
                start = Offset(Float.POSITIVE_INFINITY, 0f),
                end = Offset.Zero,
            )
            Angle.CW225 -> GradientOffset(
                start = Offset.Infinite,
                end = Offset.Zero,
            )
            Angle.CW270 -> GradientOffset(
                start = Offset(0f, Float.POSITIVE_INFINITY),
                end = Offset.Zero,
            )
            Angle.CW315 -> GradientOffset(
                start = Offset(0f, Float.POSITIVE_INFINITY),
                end = Offset(Float.POSITIVE_INFINITY, 0f),
            )
            else -> GradientOffset(
                start = Offset.Zero,
                end = Offset(Float.POSITIVE_INFINITY, 0f),
            )
        }

        override fun toBrush(): Brush =
            Brush.linearGradient(colors, gradientOffset.start, gradientOffset.end)

        override fun getColorValue() = colors.first()

        private data class GradientOffset(val start: Offset, val end: Offset)

        enum class Angle {
            CW0, CW45, CW90, CW135, CW180, CW225, CW270, CW315
        }

        companion object {
            val Unspecified = Gradient(listOf(Color.Unspecified, Color.Unspecified))
        }
    }

    data class BackgroundContentStroke(
        val background: SingleColorWrapper,
        val content: SingleColorWrapper,
        val stroke: SingleColorWrapper = Simple(Color.Transparent),
    ) : ColorWrapper {
        override fun toBrush(): Brush = background.toBrush()
        override fun getColorValue() = background.getColorValue()
    }
}
