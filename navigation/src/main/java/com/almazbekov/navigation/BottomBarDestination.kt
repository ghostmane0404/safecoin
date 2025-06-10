package com.almazbekov.navigation

import com.almazbekov.navigation.util.Destination
import kotlinx.serialization.Serializable

@Serializable
sealed interface BottomBarDestination : Destination {
    @Serializable
    data object Discover : BottomBarDestination

    @Serializable
    data object Wallet : BottomBarDestination

    @Serializable
    data object Profile : BottomBarDestination
}
