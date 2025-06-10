package com.almazbekov.bottom.tab

import androidx.compose.ui.graphics.painter.Painter
import com.almazbekov.navigation.util.Destination

data class BottomNavItem(
    val route: Destination,
    val icon: Painter,
    val selectedIcon: Painter = icon,
)
