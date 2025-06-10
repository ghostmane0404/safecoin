package com.almazbekov.safecoin.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import com.almazbekov.navigation.util.Destination
import com.almazbekov.navigation.util.Navigator as navigator

class NavigatorImpl(
    private val backStack: NavBackStack,
) : navigator {

    override fun navigateTo(destination: Destination) {
        backStack.add(destination)
    }

    override fun navigateBack() {
        backStack.removeAt(backStack.lastIndex)
    }

    override fun navigateWithPopUp(
        destination: Destination,
        popUpTo: Destination,
        inclusive: Boolean,
    ) {
        val popUpToIndex = backStack.indexOfLast { it == popUpTo }
        if (popUpToIndex != -1) {
            val endIndex = if (inclusive) popUpToIndex else popUpToIndex + 1
            if (endIndex < backStack.size) {
                backStack.subList(endIndex, backStack.size).clear()
            }
        }
        backStack.add(destination)
    }
}

val LocalNavigator = compositionLocalOf<navigator> {
    error("Navigator not provided")
}
