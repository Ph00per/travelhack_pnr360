package com.phooper.travelhack.presentation.after_shoot_instruction

import com.phooper.travelhack.App
import com.phooper.travelhack.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AfterShootInstructionPresenter : MvpPresenter<AfterShootInstructionView>() {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        CoroutineScope(Main).launch {
            delay(10)
            router.newRootScreen(Screens.EntryScreenTablet)
        }

    }
}