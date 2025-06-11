package com.almazbekov.welcome.domain

interface WelcomeShownRepository {
    val isShown: Boolean
    fun setShown(isShown: Boolean)
}
