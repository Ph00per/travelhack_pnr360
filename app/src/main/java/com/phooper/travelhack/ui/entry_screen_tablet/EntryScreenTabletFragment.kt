package com.phooper.travelhack.ui.entry_screen_tablet

import android.os.Bundle
import com.phooper.travelhack.R
import com.phooper.travelhack.presentation.entry_screen_tablet.EntryScreenTabletPresenter
import com.phooper.travelhack.presentation.entry_screen_tablet.EntryScreenTabletView
import com.phooper.travelhack.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_start_screen.*
import moxy.presenter.InjectPresenter

class EntryScreenTabletFragment : BaseFragment(), EntryScreenTabletView {
    override val layoutRes = R.layout.fragment_start_screen

    @InjectPresenter
    lateinit var presenter: EntryScreenTabletPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()

    }

    private fun initViews() {
        clickable_start_layout.setOnClickListener {
            presenter.onClicked()
        }
    }
}