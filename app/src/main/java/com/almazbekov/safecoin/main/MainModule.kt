package com.almazbekov.safecoin.main

import com.almazbekov.main.controller.MainAction
import com.almazbekov.main.controller.MainEvent
import com.almazbekov.main.controller.MainState
import com.almazbekov.main.controller.sideffect.InitializeSideEffect
import com.almazbekov.udf.EventDispatcher
import com.almazbekov.udfcompose.FlowEventDispatcher
import com.almazbekov.udfcompose.JetpackStateObservable
import com.almazbekov.udfcompose.JetpackStateObservableFactory
import com.almazbekov.udfcompose.jetpackStoreBuilder
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object MainModule {

    val module = module {
        scope<MainActivity> {
            scoped {
                MainState(screenName = "Main")
            }

            scoped {
                FlowEventDispatcher<MainEvent>()
            } bind EventDispatcher::class

            scoped<JetpackStateObservable<MainState>> {
                JetpackStateObservableFactory().create(initialState = get())
            }
            scoped {
                InitializeSideEffect(
                    welcomeShownRepository = get(),
                    eventDispatcher = get(),
                )
            }
            scoped {
                jetpackStoreBuilder<MainAction, MainState, MainEvent> {
                    stateObservable = get()
                    eventDispatcher = get()
                    sideEffects.appendAll(
                        get<InitializeSideEffect>(),
                    )
                }
            }
            viewModel {
                MainViewModel(
                    store = get(),
                    navigator = get(),
                    backStack = get(),
                )
            }
        }
    }
}
