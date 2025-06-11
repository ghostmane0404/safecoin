package com.almazbekov.welcome.controller

import com.almazbekov.udf.Action

sealed interface WelcomeAction : Action {
    data object Finish : WelcomeAction
}
