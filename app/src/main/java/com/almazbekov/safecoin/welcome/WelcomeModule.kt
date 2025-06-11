package com.almazbekov.safecoin.welcome

import com.almazbekov.udf.EventDispatcher
import com.almazbekov.udfcompose.FlowEventDispatcher
import com.almazbekov.udfcompose.JetpackStateObservable
import com.almazbekov.udfcompose.JetpackStateObservableFactory
import com.almazbekov.udfcompose.jetpackStoreBuilder
import com.almazbekov.welcome.controller.WelcomeAction
import com.almazbekov.welcome.controller.WelcomeEvent
import com.almazbekov.welcome.controller.WelcomeState
import com.almazbekov.welcome.controller.sideeffect.FinishSideEffect
import com.almazbekov.welcome.data.WelcomeShownRepositoryImpl
import com.almazbekov.welcome.domain.WelcomeShownRepository
import com.almazbekov.welcome.ui.WelcomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

object WelcomeModule {
    val module = module {
        single<WelcomeShownRepository> {
            WelcomeShownRepositoryImpl(appPreferences = get())
        }
        scope<WelcomeViewModel> {
            scoped {
                WelcomeState(screenName = "Welcome")
            }
            scoped {
                FlowEventDispatcher<WelcomeEvent>()
            } bind EventDispatcher::class

            scoped<JetpackStateObservable<WelcomeState>> {
                JetpackStateObservableFactory().create(initialState = get<WelcomeState>())
            }
            scoped {
                FinishSideEffect(
                    welcomeShownRepository = get(),
                    eventDispatcher = get(),
                )
            }
            scoped {
                jetpackStoreBuilder<
                    WelcomeAction,
                    WelcomeState,
                    WelcomeEvent,
                    > {
                    stateObservable = get()
                    eventDispatcher = get()
                    sideEffects.appendAll(
                        get<FinishSideEffect>(),
                    )
                }
            }
        }
        viewModelOf(::WelcomeViewModel)
    }
}
