package com.almazbekov.safecoin.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.almazbekov.navigation.BottomBarDestination
import com.almazbekov.navigation.util.Navigator
import org.koin.dsl.module

object NavigationModule {
    val module = module {
        single<NavBackStack> {
            mutableStateListOf<NavKey>(BottomBarDestination.Discover)
        }
        single<Navigator> {
            NavigatorImpl(backStack = get())
        }
    }
}
