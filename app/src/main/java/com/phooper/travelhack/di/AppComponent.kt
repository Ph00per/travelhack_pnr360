package com.phooper.travelhack.di

import com.phooper.travelhack.model.interactor.BarcodeScannerInteractor
import com.phooper.travelhack.model.interactor.TakePhotoCameraInteractor
import com.phooper.travelhack.model.interactor.TakePhotoTabletInteractor
import com.phooper.travelhack.presentation.after_shoot_instruction.AfterShootInstructionPresenter
import com.phooper.travelhack.presentation.barcode_scanner.BarcodeScannerPresenter
import com.phooper.travelhack.presentation.entry_screen_tablet.EntryScreenTabletPresenter
import com.phooper.travelhack.presentation.mode_selector.ModeSelectorPresenter
import com.phooper.travelhack.presentation.take_photo_camera.TakePhotoCameraPresenter
import com.phooper.travelhack.presentation.take_photo_tablet.TakePhotoTabletPresenter
import com.phooper.travelhack.ui.AppActivity
import com.phooper.travelhack.ui.global.adapters.SquareImgAdapter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: AppActivity)

    fun inject(presenter: ModeSelectorPresenter)
    fun inject(presenter: BarcodeScannerPresenter)
    fun inject(presenter: TakePhotoCameraPresenter)
    fun inject(presenter: TakePhotoTabletPresenter)
    fun inject(presenter: AfterShootInstructionPresenter)
    fun inject(presenter: EntryScreenTabletPresenter)

    fun inject(adapter: SquareImgAdapter)

    fun inject(interactor: TakePhotoTabletInteractor)
    fun inject(interactor: TakePhotoCameraInteractor)
    fun inject(interactor: BarcodeScannerInteractor)

}
