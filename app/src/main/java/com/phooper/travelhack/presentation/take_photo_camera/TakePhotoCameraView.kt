package com.phooper.travelhack.presentation.take_photo_camera

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType


interface TakePhotoCameraView : MvpView {
    @StateStrategyType(value = SkipStrategy::class)
    fun askForCameraPermission()

    @StateStrategyType(value = SkipStrategy::class)
    fun checkCameraPermission()

    @StateStrategyType(SkipStrategy::class)
    fun takePhoto()

    @StateStrategyType(SkipStrategy::class)
    fun startCounting()

}