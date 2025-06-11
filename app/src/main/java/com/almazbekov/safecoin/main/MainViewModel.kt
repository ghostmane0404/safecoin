package com.almazbekov.safecoin.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import com.almazbekov.main.controller.MainAction
import com.almazbekov.navigation.util.Navigator

class MainViewModel(
    val store: MainStore,
    val navigator: Navigator,
    val backStack: NavBackStack,
) : ViewModel() {
    init {
        val sendAction = store.getSendAction(viewModelScope)
        sendAction.invoke(MainAction.Initialize)
    }
}
