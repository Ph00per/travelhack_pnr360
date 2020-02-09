package com.phooper.travelhack.ui.barcode_scanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import com.phooper.travelhack.R
import com.phooper.travelhack.presentation.barcode_scanner.BarcodeScannerPresenter
import com.phooper.travelhack.presentation.barcode_scanner.BarcodeScannerView
import com.phooper.travelhack.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_barcode_scanner.*
import moxy.presenter.InjectPresenter


class BarcodeScannerFragment : BaseFragment(), BarcodeScannerView {

    override val layoutRes = R.layout.fragment_barcode_scanner

    var withDialogsConstrainSet: ConstraintSet? = null
    var noDialogsConstrainSet: ConstraintSet? = null

    @InjectPresenter
    lateinit var presenter: BarcodeScannerPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    //Permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                presenter.isCameraPermissionGranted(false)
            }
        }
    }

    override fun askForCameraPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CODE
        )
    }

    override fun checkCameraPermission() {
        presenter.isCameraPermissionGranted(
            ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    //////////////////////////////////////////

    private fun initViews() {
        barcode_scanner.apply {
            setStatusText("")
            barcodeView.decoderFactory = DefaultDecoderFactory(
                listOf(
                    BarcodeFormat.CODE_39
                )
            )
            initializeFromIntent(Intent())
            decodeContinuous(callback)
            cameraSettings.isAutoFocusEnabled = true
        }
        back_btn.setOnClickListener {
            presenter.backBtnOnPressed()
        }
        noDialogsConstrainSet = ConstraintSet().apply {
            clone(barcode_constraint)
        }
        withDialogsConstrainSet = ConstraintSet().apply {
            clone(barcode_constraint)
            clear(dialogs_layout.id, ConstraintSet.END)
            connect(
                dialogs_layout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
            )
        }
    }

    override fun showDialog() {
        TransitionManager.beginDelayedTransition(
            barcode_constraint,
            AutoTransition().apply { duration = 250 })
        withDialogsConstrainSet?.applyTo(barcode_constraint)
    }

    override fun hideDialog() {
        TransitionManager.beginDelayedTransition(
            barcode_constraint,
            AutoTransition().apply { duration = 250 })
        noDialogsConstrainSet?.applyTo(barcode_constraint)
    }

    private val callback: BarcodeCallback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            presenter.onCodeScanned(result.text)
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }

    override fun onResume() {
        super.onResume()
        barcode_scanner.resume()
    }

    override fun onPause() {
        barcode_scanner.pause()
        super.onPause()
    }

    override fun makeBeepSound() = BeepManager(activity).playBeepSoundAndVibrate()

    override fun onBackPressed() = presenter.onBackPressed()
}