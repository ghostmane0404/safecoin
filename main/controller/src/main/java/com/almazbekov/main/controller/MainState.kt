package com.almazbekov.main.controller

import com.almazbekov.udf.State

data class MainState(
    override val screenName: String,
) : State
