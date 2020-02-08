package com.phooper.travelhack.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.phooper.travelhack.App
import com.phooper.travelhack.R
import com.phooper.travelhack.Screens
import com.phooper.travelhack.ui.global.BaseFragment
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class AppActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = object : SupportAppNavigator(this, R.id.container) {
        override fun setupFragmentTransaction(
            command: Command?,
            currentFragment: Fragment?,
            nextFragment: Fragment?,
            fragmentTransaction: FragmentTransaction?
        ) {
            super.setupFragmentTransaction(
                command,
                currentFragment,
                nextFragment,
                fragmentTransaction
            )
            if (command is Forward) fragmentTransaction?.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_right,
                R.anim.slide_out_right
            )
        }
    }

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        App.daggerComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        if (currentFragment == null) {
            router.replaceScreen(Screens.ModeSelector)
        }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}