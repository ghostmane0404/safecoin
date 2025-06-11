package com.almazbekov.welcome.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.almazbekov.coreui.components.button.default.ButtonStyle
import com.almazbekov.coreui.components.button.default.DefaultButton
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.udfcompose.JetpackStatefulStore
import com.almazbekov.ui.contracts.ButtonState
import com.almazbekov.welcome.controller.WelcomeAction
import com.almazbekov.welcome.controller.WelcomeEvent
import com.almazbekov.welcome.controller.WelcomeState
import org.koin.androidx.compose.koinViewModel

typealias WelcomeStore = JetpackStatefulStore<WelcomeAction, WelcomeState, WelcomeEvent>

@Composable
fun WelcomeScreen() {
    val viewModel = koinViewModel<WelcomeViewModel>()
    val sendAction = viewModel.store.getSendAction(rememberCoroutineScope())

    EventHandler(
        eventDispatcher = viewModel.store.eventDispatcher,
        navigator = viewModel.navigator,
    )

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Welcome!")
            Spacer(Modifier.size(AppTheme.dimens.size4))
            DefaultButton(
                text = "Finish",
                buttonStyle = ButtonStyle.PRIMARY,
                buttonState = ButtonState.ACTIVE,
            ) {
                sendAction.invoke(WelcomeAction.Finish)
            }
        }
    }
}
