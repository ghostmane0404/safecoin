package com.almazbekov.safecoin.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.almazbekov.navigation.BottomBarDestination
import com.almazbekov.navigation.util.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideNavBackStack(): NavBackStack {
        return mutableStateListOf<NavKey>(BottomBarDestination.Discover)
    }

    @Provides
    @Singleton
    fun provideNavigator(backStack: NavBackStack): Navigator {
        return NavigatorImpl(backStack)
    }
}
