package com.almazbekov.safecoin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.almazbekov.coreui.R
import com.almazbekov.coreui.components.button.default.ButtonStyle
import com.almazbekov.coreui.components.button.default.DefaultButton
import com.almazbekov.coreui.components.button.rounded.RoundedButton
import com.almazbekov.coreui.components.button.rounded.RoundedButtonStyle
import com.almazbekov.coreui.components.input.TextInput
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.safecoin.ui.theme.SafeCoinTheme
import com.almazbekov.ui.contracts.ButtonState
import com.almazbekov.ui.contracts.InputState
import com.almazbekov.ui.contracts.InputStatus

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().background(AppTheme.colors.backgroundPrimary),
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val inputState = remember {
        mutableStateOf(
            InputState.Simple(
                status = InputStatus.Loading,
                value = "",
            ),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary)
            .padding(horizontal = AppTheme.dimens.size4),
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
        Spacer(modifier = Modifier.size(AppTheme.dimens.size4))
        DefaultButton(
            buttonState = ButtonState.ACTIVE,
            buttonStyle = ButtonStyle.PRIMARY,
            text = "Click Me",
        ) {
            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
        }
        Spacer(modifier = Modifier.size(AppTheme.dimens.size4))
        DefaultButton(
            buttonState = ButtonState.ACTIVE,
            buttonStyle = ButtonStyle.SECONDARY,
            text = "Click Me2",
        ) {
            Toast.makeText(context, "Hello2", Toast.LENGTH_SHORT).show()
        }
        Spacer(modifier = Modifier.size(AppTheme.dimens.size4))
        TextInput(
            onValueChange = {
                inputState.value = inputState.value.copy(
                    value = it,
                )
            },
            placeholder = "Add some text",
            inputState = inputState.value,
            keyboardController = keyboardController,
        )
        Spacer(modifier = Modifier.size(AppTheme.dimens.size4))
        RoundedButton(
            buttonState = ButtonState.ACTIVE,
            buttonStyle = RoundedButtonStyle.PRIMARY,
            drawableRes = R.drawable.ic_receive_14,
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SafeCoinTheme {
        Greeting("Android")
    }
}
