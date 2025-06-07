package com.almazbekov.udf

interface EventDispatcher<E : Event> {
    suspend fun dispatch(event: E)
    suspend fun dispatchIfActive(event: E)
}
