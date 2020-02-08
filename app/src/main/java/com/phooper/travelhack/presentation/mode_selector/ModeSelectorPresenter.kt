package com.phooper.travelhack.presentation.mode_selector

import com.phooper.travelhack.App
import com.phooper.travelhack.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ModeSelectorPresenter : MvpPresenter<ModeSelectorView>() {
    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    fun startTakePhotosMode() {
        router.newRootScreen(Screens.TakePhotoCamera)
    }

    fun startEditPhotosMode() {
        router.newRootScreen(Screens.LanguageSelector)
    }

}