package com.almazbekov.main.controller.sideffect

import com.almazbekov.main.controller.MainAction
import com.almazbekov.main.controller.MainEvent
import com.almazbekov.udf.EventDispatcher
import com.almazbekov.udf.SideEffect
import com.almazbekov.welcome.domain.WelcomeShownRepository

class InitializeSideEffect(
    private val welcomeShownRepository: WelcomeShownRepository,
    private val eventDispatcher: EventDispatcher<MainEvent>,
) : SideEffect<MainAction.Initialize> {
    override suspend fun execute(action: MainAction.Initialize) {
        val isWelcomeShown = welcomeShownRepository.isShown
        if (!isWelcomeShown) {
            eventDispatcher.dispatch(MainEvent.OpenWelcome)
        }
    }
}
