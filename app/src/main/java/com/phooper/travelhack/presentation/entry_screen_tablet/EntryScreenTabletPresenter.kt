package com.phooper.travelhack.presentation.entry_screen_tablet

import com.phooper.travelhack.App
import com.phooper.travelhack.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class EntryScreenTabletPresenter : MvpPresenter<EntryScreenTabletView>() {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    fun onClicked() {
        router.navigateTo(Screens.BarcodeScanner)
    }

}