package com.phooper.travelhack.ui.after_shoot_instruction

import com.phooper.travelhack.R
import com.phooper.travelhack.presentation.after_shoot_instruction.AfterShootInstructionPresenter
import com.phooper.travelhack.presentation.after_shoot_instruction.AfterShootInstructionView
import com.phooper.travelhack.ui.global.BaseFragment
import moxy.presenter.InjectPresenter

class AfterShootInstructionFragment: BaseFragment(), AfterShootInstructionView {

    @InjectPresenter
    lateinit var presenter : AfterShootInstructionPresenter

    override val layoutRes = R.layout.fragment_after_shoot_instruction


}