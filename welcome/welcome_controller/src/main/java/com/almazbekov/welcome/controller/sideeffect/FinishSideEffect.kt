package com.almazbekov.welcome.controller.sideeffect

import com.almazbekov.udf.EventDispatcher
import com.almazbekov.udf.SideEffect
import com.almazbekov.welcome.controller.WelcomeAction
import com.almazbekov.welcome.controller.WelcomeEvent
import com.almazbekov.welcome.domain.WelcomeShownRepository

class FinishSideEffect(
    private val welcomeShownRepository: WelcomeShownRepository,
    private val eventDispatcher: EventDispatcher<WelcomeEvent>,
) : SideEffect<WelcomeAction.Finish> {
    override suspend fun execute(action: WelcomeAction.Finish) {
        welcomeShownRepository.setShown(isShown = true)
        eventDispatcher.dispatch(WelcomeEvent.GoDiscover)
    }
}
