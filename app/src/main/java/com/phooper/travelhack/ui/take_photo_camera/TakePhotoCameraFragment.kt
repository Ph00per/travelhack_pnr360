package com.phooper.travelhack.ui.take_photo_camera

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.Flash
import com.phooper.travelhack.R
import com.phooper.travelhack.presentation.take_photo_camera.TakePhotoCameraPresenter
import com.phooper.travelhack.presentation.take_photo_camera.TakePhotoCameraView
import com.phooper.travelhack.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_take_photo_camera.*
import moxy.presenter.InjectPresenter

class TakePhotoCameraFragment : BaseFragment(), TakePhotoCameraView {

    @InjectPresenter
    lateinit var presenter: TakePhotoCameraPresenter

    override val layoutRes = R.layout.fragment_take_photo_camera

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {

        camera_view.apply {
            flash = Flash.ON
            setLifecycleOwner(this@TakePhotoCameraFragment)
            addCameraListener(object : CameraListener() {
                override fun onPictureTaken(result: PictureResult) {
                    super.onPictureTaken(result)
                    result.data.let{
                        presenter.photoTaken(it)

                    }
                }
            })
        }
    }

    override fun startCounting() {
        MediaPlayer.create(context, R.raw.countdown_10_secs).start()
    }

    override fun takePhoto() {
        camera_view.takePicture()
    }

    //Permissions
    override fun askForCameraPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            IntentIntegrator.REQUEST_CODE
        )
    }

    override fun checkCameraPermission() {
        presenter.isCameraPermissionGranted(
            isGranted = ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    //////////////////////////////////////////


}