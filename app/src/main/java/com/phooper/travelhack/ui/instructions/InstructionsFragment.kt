package com.phooper.travelhack.ui.instructions

import android.os.Bundle
import com.phooper.travelhack.R
import com.phooper.travelhack.presentation.instructions.InstructionsPresenter
import com.phooper.travelhack.presentation.instructions.InstructionsView
import com.phooper.travelhack.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_instructions.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class InstructionsFragment : BaseFragment(), InstructionsView {

    @InjectPresenter
    lateinit var presenter : InstructionsPresenter

    override val layoutRes = R.layout.fragment_instructions

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    initView()
    }

    private fun initView() {
        back_btn.setOnClickListener {
            presenter.backBtnOnClicked()
        }
        forward_btn.setOnClickListener {
            presenter.forwardBtnOnClicked()
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()

}