package com.almazbekov.safecoin.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.almazbekov.bottom.tab.BottomNavItem
import com.almazbekov.bottom.tab.BottomNavigationBar
import com.almazbekov.coreui.R
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.main.controller.MainAction
import com.almazbekov.main.controller.MainEvent
import com.almazbekov.main.controller.MainState
import com.almazbekov.navigation.BottomBarDestination
import com.almazbekov.navigation.WelcomeDestination
import com.almazbekov.navigation.util.Destination
import com.almazbekov.udfcompose.JetpackStatefulStore
import com.almazbekov.welcome.ui.WelcomeScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

typealias MainStore = JetpackStatefulStore<MainAction, MainState, MainEvent>

@Suppress("LongMethod")
@Composable
fun MainContentElement(backStack: NavBackStack, mainViewModel: MainViewModel) {
    val bottomNavItems = listOf(
        BottomNavItem(
            route = BottomBarDestination.Discover,
            icon = painterResource(R.drawable.ic_feed_24),
        ),
        BottomNavItem(
            route = BottomBarDestination.Wallet,
            icon = painterResource(R.drawable.ic_wallet_24),
        ),
        BottomNavItem(
            route = BottomBarDestination.Profile,
            icon = painterResource(R.drawable.ic_profile_24),
        ),
    )
    val currentRoute = backStack[backStack.lastIndex]
    val shouldShowBottomBar = currentRoute is BottomBarDestination
    val systemUiController = rememberSystemUiController()
    val backgroundColor = AppTheme.colors.backgroundPrimary

    EventHandler(backStack = backStack, eventDispatcher = mainViewModel.store.eventDispatcher)

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = backgroundColor,
            darkIcons = false,
        )
    }

    Scaffold(
        containerColor = AppTheme.colors.backgroundPrimary,
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(
                    backStack = backStack as SnapshotStateList<Destination>,
                    items = bottomNavItems,
                )
            }
        },
    ) { paddingValues ->
        NavDisplay(
            modifier = Modifier.padding(paddingValues),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { route ->
                when (route) {
                    is BottomBarDestination.Discover -> NavEntry(route) {
                        Text("Discover", color = AppTheme.colors.textPrimary)
                    }

                    is BottomBarDestination.Wallet -> NavEntry(route) {
                        Text("Wallet", color = AppTheme.colors.textPrimary)
                    }

                    is BottomBarDestination.Profile -> NavEntry(route) {
                        Text("Settings", color = AppTheme.colors.textPrimary)
                    }

                    is WelcomeDestination -> NavEntry(route) {
                        WelcomeScreen()
                    }

                    else -> NavEntry(route) {}
                }
            },
        )
    }
}
