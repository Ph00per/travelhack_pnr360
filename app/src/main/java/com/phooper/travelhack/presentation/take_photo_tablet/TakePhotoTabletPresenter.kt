package com.phooper.travelhack.presentation.take_photo_tablet

import com.phooper.travelhack.App
import com.phooper.travelhack.R
import com.phooper.travelhack.Screens
import com.phooper.travelhack.model.interactor.TakePhotoTabletInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
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

//    @Inject
//    lateinit var interactor: TakePhotoTabletInteractor

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun startBtnOnClicked() {
//        CoroutineScope(IO).launch {
//            interactor.setPhotoState(barcode!!)
//        }
        startCounting()

    }

    private fun startCounting() {
        CoroutineScope(Main).launch {
            viewState.apply {
                hideHintLayout()
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

    fun btnExitOnClicked() {
        router.newRootScreen(Screens.EntryScreenTablet)
    }
}
