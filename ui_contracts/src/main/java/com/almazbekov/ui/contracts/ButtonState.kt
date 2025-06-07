package com.almazbekov.ui.contracts

enum class ButtonState {
    DISABLED,
    ACTIVE,
    LOADING,
    ;

    fun isLoading() = this == LOADING
    fun isActive() = this == ACTIVE
    fun isEnabled() = this != DISABLED
}
