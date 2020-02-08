package com.phooper.travelhack.presentation.take_photo_tablet

import com.phooper.travelhack.App
import com.phooper.travelhack.R
import com.phooper.travelhack.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class TakePhotoTabletPresenter(private val barcode: String?) : MvpPresenter<TakePhotoTabletView>() {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    fun startBtnOnClicked() {
        //TODO IS CONNECTED TO SERVER?
        startCounting()
    }

    private fun startCounting() {
        CoroutineScope(Main).launch {
            viewState.apply {
                disableStartBtn()
                changeStartBtnResource(R.drawable.blue_white_circle)
                showStartBtnDigits()
                repeat(10) {
                    viewState.changeStartBtnDigit((10 - it).toString())
                    delay(1000)
                }
                //TODO LOAD IMG
                hideStartBtnDigits()
                changeStartBtnResource(R.drawable.ic_start)
            }
//TODO Next 5 sec instr screen            router.navigateTo()
        }
    }

    fun dialogCancelBtnOnClicked() {
        viewState.hideDropSessionDlg()
    }

    fun dialogExitBtnOnClicked() {
        //TODO DROP SESSION
        router.newRootScreen(Screens.LanguageSelector)
    }

    fun btnExitOnClicked() {
        viewState.showDropSessionDlg()
    }
}
