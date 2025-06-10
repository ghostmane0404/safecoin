package com.almazbekov.safecoin.navigation

import androidx.navigation3.runtime.NavBackStack
import com.almazbekov.navigation.util.Navigator
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NavigationEntryPoint {
    fun navigator(): Navigator
    fun backStack(): NavBackStack
}
