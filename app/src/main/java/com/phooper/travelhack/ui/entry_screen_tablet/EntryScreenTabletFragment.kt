package com.phooper.travelhack.ui.entry_screen_tablet

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import androidx.constraintlayout.widget.ConstraintSet
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

    private var firstConstr: ConstraintSet? = null
    private var secondConstr: ConstraintSet? = null

    var changed = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()

    }

    private fun initViews() {
        clickable_start_layout.setOnClickListener {
            presenter.onClicked()
        }
        firstConstr = ConstraintSet().apply {
            clone(clickable_start_layout)
            setVerticalBias(barcode_img.id, 0.30f)
        }
        secondConstr = ConstraintSet().apply {
            clone(clickable_start_layout)
            setVerticalBias(barcode_img.id, 0.5f)
        }
    }

    override fun animate() {
        TransitionManager.beginDelayedTransition(clickable_start_layout)
        changed = if (changed) {
            firstConstr?.applyTo(clickable_start_layout)
            false
        } else {
            secondConstr?.applyTo(clickable_start_layout)
            true
        }

    }

}