package com.almazbekov.bottom.tab

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.navigation.util.Destination

@Composable
fun BottomNavigationBar(
    backStack: SnapshotStateList<Destination>,
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
) {
    val currentRoute = backStack[backStack.lastIndex]

    NavigationBar(
        modifier = modifier,
        containerColor = AppTheme.colors.backgroundPrimary,
        tonalElevation = 8.dp,
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = if (isSelected) item.selectedIcon else item.icon,
                        contentDescription = null,
                    )
                },
                label = {},
                selected = isSelected,
                onClick = {
                    if (!isSelected) navigateToBottomBarRoute(backStack, item.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppTheme.colors.iconPrimary,
                    unselectedIconColor = AppTheme.colors.iconSecondary,
                    indicatorColor = AppTheme.colors.transparent,
                ),
            )
        }
    }
}

private fun navigateToBottomBarRoute(
    backStack: SnapshotStateList<Destination>,
    targetRoute: Destination,
) {
    when (val existingIndex = backStack.indexOfFirst { it == targetRoute }) {
        -1 -> backStack.add(targetRoute)

        backStack.lastIndex -> return

        else -> while (backStack.lastIndex > existingIndex) {
            backStack.removeAt(backStack.lastIndex)
        }
    }
}
