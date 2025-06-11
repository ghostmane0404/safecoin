package com.almazbekov.welcome.data

import com.almazbekov.core.preferences.AppPreferences
import com.almazbekov.welcome.domain.WelcomeShownRepository

class WelcomeShownRepositoryImpl(
    private val appPreferences: AppPreferences,
) : WelcomeShownRepository {
    companion object {
        private const val WELCOME_ONBOARDING = "WELCOME_ONBOARDING"
    }

    override val isShown: Boolean
        get() = appPreferences.getBoolean(WELCOME_ONBOARDING)

    override fun setShown(isShown: Boolean) {
        appPreferences.setBoolean(WELCOME_ONBOARDING, isShown)
    }
}
