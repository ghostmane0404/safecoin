package com.almazbekov.main.controller

import com.almazbekov.udf.Event

sealed interface MainEvent : Event {
    data object OpenWelcome : MainEvent
}
