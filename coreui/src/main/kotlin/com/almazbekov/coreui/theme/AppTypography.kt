package com.almazbekov.coreui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.almazbekov.coreui.R

@Immutable
data class AppTypography(
    val h1: TextStyle,
    val h3: TextStyle,
    val body: TextStyle,
    val bodySemiBold: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val enlarged: TextStyle,
    val caption: TextStyle,
) {
    companion object {
        val ibmPlexMonoFontFamily = FontFamily(
            Font(
                resId = R.font.ibm_plex_mono_thin,
                weight = FontWeight.W100,
                style = FontStyle.Normal,
            ),
            Font(
                resId = R.font.ibm_plex_mono_thin_italic,
                weight = FontWeight.W100,
                style = FontStyle.Italic,
            ),
            Font(
                resId = R.font.ibm_plex_mono_extralight,
                weight = FontWeight.W200,
                style = FontStyle.Normal,
            ),
            Font(
                resId = R.font.ibm_plex_mono_extralight_italic,
                weight = FontWeight.W200,
                style = FontStyle.Italic,
            ),
            Font(
                resId = R.font.ibm_plex_mono_light,
                weight = FontWeight.W300,
                style = FontStyle.Normal,
            ),
            Font(
                resId = R.font.ibm_plex_mono_light_italic,
                weight = FontWeight.W300,
                style = FontStyle.Italic,
            ),
            Font(
                resId = R.font.ibm_plex_mono_regular,
                weight = FontWeight.W400,
                style = FontStyle.Normal,
            ),
            Font(
                resId = R.font.ibm_plex_mono_italic,
                weight = FontWeight.W400,
                style = FontStyle.Italic,
            ),
            Font(
                resId = R.font.ibm_plex_mono_medium,
                weight = FontWeight.W500,
                style = FontStyle.Normal,
            ),
            Font(
                resId = R.font.ibm_plex_mono_medium_italic,
                weight = FontWeight.W500,
                style = FontStyle.Italic,
            ),
            Font(
                resId = R.font.ibm_plex_mono_semibold,
                weight = FontWeight.W600,
                style = FontStyle.Normal,
            ),
            Font(
                resId = R.font.ibm_plex_mono_semibold_italic,
                weight = FontWeight.W600,
                style = FontStyle.Italic,
            ),
            Font(
                resId = R.font.ibm_plex_mono_bold,
                weight = FontWeight.W700,
                style = FontStyle.Normal,
            ),
            Font(
                resId = R.font.ibm_plex_mono_bold_italic,
                weight = FontWeight.W700,
                style = FontStyle.Italic,
            ),
        )
    }

    internal constructor() : this(
        h1 = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            lineHeight = TextUnit.Unspecified,
        ),
        h3 = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = TextUnit.Unspecified,
        ),
        body = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = TextUnit.Unspecified,
        ),
        bodySemiBold = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = TextUnit.Unspecified,
        ),
        body2 = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = TextUnit.Unspecified,
        ),
        button = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = TextUnit.Unspecified,
        ),
        enlarged = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = TextUnit.Unspecified,
        ),
        caption = TextStyle(
            fontFamily = ibmPlexMonoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = TextUnit.Unspecified,
        ),
    )
}
