package com.phooper.travelhack

import com.phooper.travelhack.ui.after_shoot_instruction.AfterShootInstructionFragment
import com.phooper.travelhack.ui.barcode_scanner.BarcodeScannerFragment
import com.phooper.travelhack.ui.entry_screen_tablet.EntryScreenTabletFragment
import com.phooper.travelhack.ui.mode_selector.ModeSelectorFragment
import com.phooper.travelhack.ui.take_photo_camera.TakePhotoCameraFragment
import com.phooper.travelhack.ui.take_photo_tablet.TakePhotoTabletFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screens {


    object ModeSelector : SupportAppScreen() {
        override fun getFragment() =
            ModeSelectorFragment()
    }

    object BarcodeScanner : SupportAppScreen() {
        override fun getFragment() =
            BarcodeScannerFragment()
    }

    object TakePhotoCamera : SupportAppScreen() {
        override fun getFragment() =
            TakePhotoCameraFragment()
    }


    data class TakePhotoTablet(
        val barcode: String
    ) : SupportAppScreen() {
        override fun getFragment() = TakePhotoTabletFragment.create(barcode)
    }

    object AfterShootInstr : SupportAppScreen() {
        override fun getFragment() =
            AfterShootInstructionFragment()
    }

    object EntryScreenTablet : SupportAppScreen() {
        override fun getFragment() =
            EntryScreenTabletFragment()
    }


}