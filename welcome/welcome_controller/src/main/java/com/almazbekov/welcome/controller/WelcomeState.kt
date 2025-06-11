package com.almazbekov.welcome.controller

import com.almazbekov.udf.State

data class WelcomeState(
    override val screenName: String,
) : State
