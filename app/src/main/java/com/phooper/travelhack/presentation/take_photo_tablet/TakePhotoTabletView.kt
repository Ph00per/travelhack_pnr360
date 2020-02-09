package com.phooper.travelhack.presentation.take_photo_tablet

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TakePhotoTabletView : MvpView {

    @StateStrategyType(value = SkipStrategy::class)
    fun showMessage(msg: String)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun changeStartBtnResource(resId: Int)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun changeStartBtnDigit(digit: String)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun hideStartBtnDigits()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showStartBtnDigits()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun disableStartBtn()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun hideHintAndExit()


}
