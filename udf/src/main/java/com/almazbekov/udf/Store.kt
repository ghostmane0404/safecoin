package com.almazbekov.udf

interface Store<A : Action> {
    suspend fun process(action: A)
}

interface Action
interface State {
    val screenName: String
}

interface Event
