package com.phooper.travelhack.presentation.entry_screen_tablet

import com.phooper.travelhack.App
import com.phooper.travelhack.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        CoroutineScope(IO).launch {
            while (true) {
                withContext(Main) { viewState.animate() }
                delay(1200)
            }
        }
    }

}