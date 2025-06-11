package com.almazbekov.safecoin.di

import com.almazbekov.safecoin.core.AppPreferencesModule
import com.almazbekov.safecoin.main.MainModule
import com.almazbekov.safecoin.navigation.NavigationModule

object BaseModules {
    val modules = listOf(
        NavigationModule.module,
        AppPreferencesModule.module,
        MainModule.module,
    )
}
