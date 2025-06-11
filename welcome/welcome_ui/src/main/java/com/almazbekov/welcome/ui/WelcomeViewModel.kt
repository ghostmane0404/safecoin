package com.almazbekov.welcome.ui

import com.almazbekov.navigation.util.Navigator
import org.koin.viewmodel.scope.ScopeViewModel

class WelcomeViewModel : ScopeViewModel() {
    val store: WelcomeStore by scope.inject()
    val navigator: Navigator by scope.inject()
}
