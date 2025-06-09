package com.almazbekov.ui.contracts

sealed class InputState(
    open val status: InputStatus,
    open val value: String,
) {
    data class Simple(
        override val status: InputStatus = InputStatus.Active(),
        override val value: String,
    ) : InputState(status, value)
}

sealed class InputLines {
    data object Single : InputLines()
    data class Multi(val maxLines: Int) : InputLines()

    fun isSingleLine() = this is Single
}

sealed class InputStatus {
    data object Disabled : InputStatus()
    data class Active(val shouldFocus: Boolean = false) : InputStatus()
    data class Error(val shouldFocus: Boolean = false) : InputStatus()
    data object Loading : InputStatus()

    fun shouldFocus() = when (this) {
        is Disabled, is Loading -> false
        is Active -> shouldFocus
        is Error -> shouldFocus
    }

    fun isEnabled() = this is Active || this is Error

    fun isError() = this is Error

    fun isActive() = this is Active

    fun isLoading() = this is Loading
}
