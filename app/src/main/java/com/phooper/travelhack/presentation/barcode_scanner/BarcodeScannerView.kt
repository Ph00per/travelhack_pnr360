package com.phooper.travelhack.presentation.barcode_scanner

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BarcodeScannerView : MvpView {

    @StateStrategyType(value = SkipStrategy::class)
    fun askForCameraPermission()

    @StateStrategyType(value = SkipStrategy::class)
    fun checkCameraPermission()

    @StateStrategyType(value = SkipStrategy::class)
    fun makeBeepSound()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showDialog()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun hideDialog()

}