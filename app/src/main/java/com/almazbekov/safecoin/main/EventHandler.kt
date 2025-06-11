package com.almazbekov.safecoin.main

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import com.almazbekov.main.controller.MainEvent
import com.almazbekov.navigation.WelcomeDestination
import com.almazbekov.udfcompose.FlowEventDispatcher

@Composable
fun EventHandler(
    backStack: NavBackStack,
    eventDispatcher: FlowEventDispatcher<MainEvent>,
) {
    eventDispatcher.CollectOnUi { event ->
        when (event) {
            is MainEvent.OpenWelcome -> {
                if (backStack.none { it is WelcomeDestination }) {
                    backStack.add(WelcomeDestination)
                }
            }
        }
    }
}
