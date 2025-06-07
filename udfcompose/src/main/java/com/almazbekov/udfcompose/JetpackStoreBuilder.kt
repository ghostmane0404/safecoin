package com.almazbekov.udfcompose

import com.almazbekov.udf.Action
import com.almazbekov.udf.Event
import com.almazbekov.udf.SideEffects
import com.almazbekov.udf.State

fun <A : Action, S : State> jetpackStoreBuilder(
    build: BuilderScope<A, S, Event>.() -> Unit,
) = jetpackStoreBuilder<A, S, Event>(build)

@JvmName("jetpackStoreWithEventBuilder")
fun <A : Action, S : State, E : Event> jetpackStoreBuilder(
    build: BuilderScope<A, S, E>.() -> Unit,
): JetpackStatefulStore<A, S, E> {
    val scope = BuilderScope<A, S, E>().also(build)
    return JetpackStatefulStore(
        stateObservable = scope.stateObservable,
        eventDispatcher = scope.eventDispatcher,
        sideEffects = scope.sideEffects.build(),
    )
}

class BuilderScope<A : Action, S : State, E : Event> {
    lateinit var stateObservable: JetpackStateObservable<S>
    var eventDispatcher = FlowEventDispatcher<E>()
    var sideEffects = SideEffects.Builder<A>()
}
