package com.almazbekov.welcome.ui

import androidx.compose.runtime.Composable
import com.almazbekov.navigation.BottomBarDestination
import com.almazbekov.navigation.util.Navigator
import com.almazbekov.udfcompose.FlowEventDispatcher
import com.almazbekov.welcome.controller.WelcomeEvent

@Composable
fun EventHandler(
    eventDispatcher: FlowEventDispatcher<WelcomeEvent>,
    navigator: Navigator,
) {
    eventDispatcher.CollectOnUi { event ->
        when (event) {
            WelcomeEvent.GoDiscover -> navigator.navigateWithPopUp(
                destination = BottomBarDestination.Discover,
                popUpTo = BottomBarDestination.Discover,
            )
        }
    }
}
