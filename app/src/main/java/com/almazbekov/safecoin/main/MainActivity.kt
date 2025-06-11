package com.almazbekov.safecoin.main

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.almazbekov.coreui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeActivity

class MainActivity : ScopeActivity() {
    companion object {
        private const val SPLASH_ANIMATION_DURATION = 1200L
    }

    private val viewModel: MainViewModel by scope.inject()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splash = installSplashScreen()
        configureSplashScreen(splash)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainContentElement(viewModel.backStack, viewModel)
                }
            }
        }
    }

    private fun configureSplashScreen(splashScreen: SplashScreen) {
        var keepSplashOnScreen = true

        splashScreen.setKeepOnScreenCondition { keepSplashOnScreen }

        lifecycleScope.launch {
            delay(SPLASH_ANIMATION_DURATION)
            keepSplashOnScreen = false
        }

        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.remove()
        }
    }
}
