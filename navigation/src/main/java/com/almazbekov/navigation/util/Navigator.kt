package com.almazbekov.navigation.util

interface Navigator {
    fun navigateTo(destination: Destination)
    fun navigateBack()
    fun navigateWithPopUp(
        destination: Destination,
        popUpTo: Destination,
        inclusive: Boolean = false,
    )
}
