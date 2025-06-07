package com.almazbekov.coreui.components.button.rounded

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.ui.contracts.ButtonState

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    buttonState: ButtonState,
    buttonStyle: RoundedButtonStyle,
    @DrawableRes drawableRes: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(AppTheme.dimens.size10)
            .background(
                brush = buttonStyle.getBackgroundByState(state = buttonState),
                shape = CircleShape,
            )
            .border(
                width = AppTheme.dimens.buttonStroke,
                brush = buttonStyle.getBorderColor(state = buttonState),
                shape = CircleShape,
            )
            .clip(CircleShape)
            .clickable(enabled = buttonState.isActive()) { onClick.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(AppTheme.dimens.size3Half),
            painter = painterResource(id = drawableRes),
            tint = buttonStyle.getContentColor(state = buttonState),
            contentDescription = null,
        )
    }
}
