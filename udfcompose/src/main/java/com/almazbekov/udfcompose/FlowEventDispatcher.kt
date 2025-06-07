package com.almazbekov.udfcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import com.almazbekov.udf.Event
import com.almazbekov.udf.EventDispatcher

class FlowEventDispatcher<E : Event> : EventDispatcher<E> {
    private val eventChannel = Channel<E>(capacity = Channel.BUFFERED)
    private val flow = eventChannel.receiveAsFlow()
    private val isActive = AtomicBoolean(false)

    override suspend fun dispatch(event: E) {
        eventChannel.send(event)
    }

    // drop event if composable is disposed
    override suspend fun dispatchIfActive(event: E) {
        if (isActive.get()) {
            eventChannel.send(event)
        }
    }

    fun collect(scope: CoroutineScope, collect: (E) -> Unit) {
        scope.launch {
            flow.collect { collect(it) }
        }
    }

    @Composable
    @NonRestartableComposable
    fun CollectOnUi(collect: suspend (E) -> Unit) {
        LaunchedEffect(Unit) {
            isActive.set(true)
            flow.collect {
                launch {
                    collect(it)
                }
            }
        }
        DisposableEffect(Unit) {
            onDispose {
                isActive.set(false)
            }
        }
    }
}
