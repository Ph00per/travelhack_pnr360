package com.phooper.travelhack

import android.app.Application
import com.phooper.travelhack.di.*

class App : Application() {

    companion object {
        lateinit var daggerComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}
