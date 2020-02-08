package com.phooper.travelhack.presentation.barcode_scanner

import android.util.Log
import com.phooper.travelhack.App
import com.phooper.travelhack.Screens
import com.phooper.travelhack.model.interactor.BarcodeScannerInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class BarcodeScannerPresenter : MvpPresenter<BarcodeScannerView>() {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var scannerInteractor: BarcodeScannerInteractor

    private var lastScannedBarcode: String? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.checkCameraPermission()

        //TODO Remove me
//        CoroutineScope(IO).launch {
//            delay(1000)
//            withContext(Main) {
//                router.navigateTo(Screens.TakePhotoTablet("312123321123"))
//            }
//        }
    }

    fun isCameraPermissionGranted(isGranted: Boolean) {
        if (!isGranted) {
            viewState.askForCameraPermission()
        }
    }

    fun onCodeScanned(barcode: String?) {
        if (barcode == null || barcode == lastScannedBarcode) {
            return
        }
        viewState.makeBeepSound()
        lastScannedBarcode = barcode
        Log.d("Scanned: ", barcode)
        CoroutineScope(IO).launch {
            if (scannerInteractor.validateOLD(barcode)) {
                Log.d("Scan succeed: ", "")
                withContext(Main) { router.navigateTo(Screens.TakePhotoTablet(barcode)) }
            } else {
                //TODO: Barcode scan failed
                withContext(Main) {
                    viewState.apply {
                    }
                }
                Log.d("Scan failed: ", "")
            }
        }
    }

    fun onBackPressed() = router.exit()

    fun backBtnOnPressed() = onBackPressed()
}









