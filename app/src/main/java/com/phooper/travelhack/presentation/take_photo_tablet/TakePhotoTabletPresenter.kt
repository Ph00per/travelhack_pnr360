package com.phooper.travelhack.presentation.take_photo_tablet

import android.util.Log
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

    @Inject
    lateinit var interactor: TakePhotoTabletInteractor

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun startBtnOnClicked() {
        CoroutineScope(IO).launch {
            Log.d("SEting state", barcode)
            interactor.setPhotoState(barcode!!)
        }
        startCounting()

    }

    private fun startCounting() {
        CoroutineScope(Main).launch {
            viewState.apply {
                hideHintAndExit()
                disableStartBtn()
                showStartBtnDigits()
                changeStartBtnResource(R.drawable.blue_white_circle)
                repeat(10) {
                    viewState.changeStartBtnDigit((10 - it).toString())
                    delay(1000)
                }
                router.navigateTo(Screens.AfterShootInstr)
            }
        }
    }

    fun btnExitOnClicked() {
        router.newRootScreen(Screens.EntryScreenTablet)
    }
}
