package com.almazbekov.udfcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.almazbekov.udf.Action
import com.almazbekov.udf.CloseableCoroutineScope
import com.almazbekov.udf.Event
import com.almazbekov.udf.SideEffects
import com.almazbekov.udf.State
import com.almazbekov.udf.Store
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.Collections.synchronizedList
import androidx.compose.runtime.State as ComposeState

class JetpackStatefulStore<A : Action, S : State, E : Event>(
    val stateObservable: JetpackStateObservable<S>,
    val eventDispatcher: FlowEventDispatcher<E>,
    val sideEffects: SideEffects<A>,
) : Store<A> {
    private val processingActions: MutableList<A> =
        synchronizedList(mutableListOf())
    val state: ComposeState<S>
        @Composable
        get() = stateObservable.asState()

    private val defaultStoreScope: CoroutineScope = CloseableCoroutineScope()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val singleThreadDispatcher = Dispatchers.IO.limitedParallelism(1)

    @Composable
    fun getRememberedSendAction(): (A) -> Unit {
        return getRememberedSendAction(defaultStoreScope)
    }

    @Composable
    fun getRememberedSendAction(
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = singleThreadDispatcher,
    ): (A) -> Unit {
        return remember {
            getSendAction(scope, dispatcher)
        }
    }

    fun getSendAction(
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = singleThreadDispatcher,
    ): (A) -> Unit {
        return fun(action: A) {
            if (!processingActions.contains(action)) {
                processingActions.add(action)
                scope.launch(dispatcher) { process(action) }
            }
        }
    }

    fun getSendAction(): (A) -> Unit {
        return getSendAction(defaultStoreScope)
    }

    override suspend fun process(action: A) {
        sideEffects.execute(action)
        processingActions.remove(action)
    }

    fun clearProcessingActions() {
        processingActions.clear()
    }
}
