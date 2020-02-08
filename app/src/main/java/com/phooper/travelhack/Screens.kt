package com.phooper.travelhack

import com.phooper.travelhack.ui.after_shoot_instruction.AfterShootInstructionFragment
import com.phooper.travelhack.ui.barcode_scanner.BarcodeScannerFragment
import com.phooper.travelhack.ui.instructions.InstructionsFragment
import com.phooper.travelhack.ui.language_selector.LanguageSelectorFragment
import com.phooper.travelhack.ui.mode_selector.ModeSelectorFragment
import com.phooper.travelhack.ui.take_photo_camera.TakePhotoCameraFragment
import com.phooper.travelhack.ui.take_photo_tablet.TakePhotoTabletFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screens {


    object ModeSelector : SupportAppScreen() {
        override fun getFragment() =
            ModeSelectorFragment()
    }

    object LanguageSelector : SupportAppScreen() {
        override fun getFragment() =
            LanguageSelectorFragment()
    }

    object Instructions : SupportAppScreen() {
        override fun getFragment() =
            InstructionsFragment()
    }


    object BarcodeScanner : SupportAppScreen() {
        override fun getFragment() =
            BarcodeScannerFragment()
    }

    object TakePhotoCamera : SupportAppScreen() {
        override fun getFragment() =
            TakePhotoCameraFragment()
    }


    data class TakePhotoTabletNoPhotos(
        val barcode: String
    ) : SupportAppScreen() {
        override fun getFragment() = TakePhotoTabletFragment.create(barcode)
    }


    object AfterShootInstr : SupportAppScreen() {
        override fun getFragment() =
            AfterShootInstructionFragment()
    }


}