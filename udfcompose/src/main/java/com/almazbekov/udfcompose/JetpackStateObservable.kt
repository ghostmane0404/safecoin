package com.almazbekov.udfcompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.almazbekov.udf.State
import com.almazbekov.udf.StateObservable
import kotlinx.coroutines.flow.MutableStateFlow

open class FlowStateObservable<S : State>(initialState: S) : StateObservable<S> {

    private var internalData = initialState
    protected val flow = MutableStateFlow(initialState)

    override var stateValue: S
        get() = internalData
        set(value) {
            internalData = value
            flow.value = value
        }
}

class JetpackStateObservable<S : State>(
    initialState: S,
) : FlowStateObservable<S>(initialState) {
    @Composable
    fun asState() = flow.collectAsState()
}

class JetpackStateObservableFactory : StateObservable.Factory {

    override fun <S : State> create(initialState: S): JetpackStateObservable<S> =
        JetpackStateObservable(initialState)
}
