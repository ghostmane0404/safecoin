package com.almazbekov.udf

open class SideEffects<A : Action> private constructor() {
    private val sideEffectsMap = hashMapOf<String, SideEffect<out A>>()

    suspend fun execute(action: A) {
        val value = sideEffectsMap[action.javaClass.name] as SideEffect<A>
        value.execute(action)
    }

    class Builder<A : Action> {
        private val genericTypeNameFactory = GenericTypeNameFactory()
        private val sideEffects = SideEffects<A>()

        fun appendSideEffect(
            sideEffect: SideEffect<out A>,
        ): Builder<A> {
            sideEffects.sideEffectsMap[genericTypeNameFactory.create(sideEffect::class.java)] =
                sideEffect
            return this
        }

        fun appendAll(
            vararg sideEffects: SideEffect<out A>,
        ) {
            sideEffects.forEach { appendSideEffect(it) }
        }

        operator fun plusAssign(sideEffect: SideEffect<out A>) {
            appendSideEffect(sideEffect)
        }

        fun build(): SideEffects<A> = sideEffects
    }
}
