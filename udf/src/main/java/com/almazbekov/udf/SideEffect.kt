package com.almazbekov.udf

interface SideEffect<A : Action> {
    suspend fun execute(action: A)
}
