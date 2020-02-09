package com.phooper.travelhack.presentation.entry_screen_tablet

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface EntryScreenTabletView : MvpView {

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun animate()

}