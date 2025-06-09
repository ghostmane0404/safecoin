package com.almazbekov.coreui.components.input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.almazbekov.coreui.theme.AppTheme

@Composable
internal fun TextInputDecorationBox(
    value: String,
    placeholder: String,
    innerTextField: @Composable () -> Unit,
    shouldCenter: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimens.size4),
        contentAlignment = if (shouldCenter) {
            Alignment.CenterStart
        } else {
            Alignment.TopStart
        },
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = AppTheme.typography.enlarged,
                color =
                AppTheme.colors.textTertiary,
            )
        }
        innerTextField()
    }
}
