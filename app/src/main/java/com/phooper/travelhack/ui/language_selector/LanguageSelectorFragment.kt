package com.phooper.travelhack.ui.language_selector

import android.os.Bundle
import com.phooper.travelhack.R
import com.phooper.travelhack.presentation.language_selector.LanguageSelectorPresenter
import com.phooper.travelhack.presentation.language_selector.LanguageSelectorView
import com.phooper.travelhack.ui.global.BaseFragment
import com.phooper.travelhack.utils.Constants
import kotlinx.android.synthetic.main.fragment_language_selector.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class LanguageSelectorFragment : BaseFragment(), LanguageSelectorView {


    @InjectPresenter
    lateinit var presenter: LanguageSelectorPresenter

    override val layoutRes = R.layout.fragment_language_selector

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        eng_btn.setOnClickListener {
            presenter.englishBtnOnClicked()
        }
        china_btn.setOnClickListener {
            presenter.chineeseBtnOnClicked()
        }
        rus_btn.setOnClickListener {
            presenter.russianBtnOnClicked()
        }
    }
}