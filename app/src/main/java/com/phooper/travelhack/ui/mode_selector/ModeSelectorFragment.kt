package com.phooper.travelhack.ui.mode_selector

import android.os.Bundle
import com.phooper.travelhack.R
import com.phooper.travelhack.presentation.mode_selector.ModeSelectorPresenter
import com.phooper.travelhack.presentation.mode_selector.ModeSelectorView
import com.phooper.travelhack.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_mode_selector.*
import moxy.presenter.InjectPresenter

class ModeSelectorFragment : BaseFragment(), ModeSelectorView {

    @InjectPresenter
    lateinit var presenter: ModeSelectorPresenter

    override val layoutRes = R.layout.fragment_mode_selector

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        take_photos_mode.setOnClickListener {
            presenter.startTakePhotosMode()
        }
        print_photos_mode.setOnClickListener {
            presenter.startEditPhotosMode()
        }
    }
}