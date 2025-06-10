package com.almazbekov.safecoin.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation3.runtime.NavBackStack
import com.almazbekov.coreui.theme.AppTheme
import com.almazbekov.navigation.util.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private const val SPLASH_ANIMATION_DURATION = 1200L
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var backStack: NavBackStack

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splash = installSplashScreen()
        configureSplashScreen(splash)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ActivityContentElement(backStack)
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
