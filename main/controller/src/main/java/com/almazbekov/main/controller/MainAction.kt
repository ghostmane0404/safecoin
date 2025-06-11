package com.almazbekov.main.controller

import com.almazbekov.udf.Action

sealed interface MainAction : Action {
    data object Initialize : MainAction
}
