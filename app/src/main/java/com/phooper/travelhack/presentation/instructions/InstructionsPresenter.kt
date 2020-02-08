package com.phooper.travelhack.presentation.instructions

import com.phooper.travelhack.App
import com.phooper.travelhack.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InstructionsPresenter : MvpPresenter<InstructionsView>() {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    fun forwardBtnOnClicked() {
        router.navigateTo(Screens.BarcodeScanner)
    }

    fun backBtnOnClicked() {
        router.exit()
    }

    fun onBackPressed() = backBtnOnClicked()

}