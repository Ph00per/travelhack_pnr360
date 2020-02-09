package com.phooper.travelhack.presentation.take_photo_camera

import android.util.Log
import com.phooper.travelhack.App
import com.phooper.travelhack.model.interactor.TakePhotoCameraInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


@InjectViewState
class TakePhotoCameraPresenter : MvpPresenter<TakePhotoCameraView>() {

    init {
        App.daggerComponent.inject(this)
    }

    var currentBarcode: String? = null

    @Inject
    lateinit var interactor: TakePhotoCameraInteractor


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.checkCameraPermission()

        listenForData()

    }

    private fun listenForData() {
        CoroutineScope(IO).launch {
            while (true) {
//                Log.d("CHECKIN", "CHECKIN")
                delay(300)
                interactor.getCurrentPhotoFlag()?.let {
                    currentBarcode = it
                    interactor.clearFlag(it)
                    startCounting()
                    return@launch
                }

            }
        }
    }


    fun isCameraPermissionGranted(isGranted: Boolean) {
        if (!isGranted) {
            viewState.askForCameraPermission()
        }
    }

    suspend fun startCounting() =
        withContext(Main) {
            viewState.startCounting()
            delay(9300)
            viewState.takePhoto()
            listenForData()
        }

    fun photoTaken(photo: ByteArray) {
        //TODO SEND PHOTO
//        Log.d("is Photo null", (photo == null).toString())
//        Log.d("Photo size", photo?.byteCount.toString())
//        Log.d("Photo id", currentBarcode)
        CoroutineScope(IO).launch {

            Log.d("current bar null", (currentBarcode == null).toString())
            currentBarcode?.let {
                Log.d("Sending photo", it)
                interactor.sendPhoto(it, photo)
            }
            currentBarcode = null
        }
    }
}
