package com.almazbekov.welcome.controller

import com.almazbekov.udf.Event

sealed interface WelcomeEvent : Event {
    data object GoDiscover : WelcomeEvent
}
