package com.almazbekov.udf

interface StateObservable<S : State> {

    var stateValue: S

    fun update(action: (S) -> S) {
        stateValue = action(stateValue)
    }

    interface Factory {
        fun <S : State> create(initialState: S): StateObservable<S>
    }
}
