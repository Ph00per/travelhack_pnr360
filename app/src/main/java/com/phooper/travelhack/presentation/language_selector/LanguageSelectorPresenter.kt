package com.phooper.travelhack.presentation.language_selector

import com.phooper.travelhack.App
import com.phooper.travelhack.Screens
import com.phooper.travelhack.utils.Constants
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class LanguageSelectorPresenter : MvpPresenter<LanguageSelectorView>() {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var router: Router


    fun englishBtnOnClicked() {

    }

    fun chineeseBtnOnClicked() {
    }

    fun russianBtnOnClicked() {
        router.navigateTo(Screens.Instructions)
    }
}