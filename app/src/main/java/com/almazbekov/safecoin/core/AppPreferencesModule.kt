package com.almazbekov.safecoin.core

import com.almazbekov.core.android.preferences.AppPreferencesImpl
import com.almazbekov.core.preferences.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppPreferencesModule {
    private const val PREFERENCES_NAME = "safecoin_preferences"
    val module = module {
        single<AppPreferences> {
            AppPreferencesImpl(
                context = androidContext(),
                name = PREFERENCES_NAME,
            )
        }
    }
}
