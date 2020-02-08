package com.phooper.travelhack.model.interactor

import com.phooper.travelhack.App
import com.phooper.travelhack.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject


class BarcodeScannerInteractor {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var okhttpClient: OkHttpClient

    suspend fun validate(barcode: String): Boolean? = withContext(IO) {
        okhttpClient.newCall(
            Request.Builder()
                .url(Constants.BASE_URI + "api/users/${barcode}/isActive")
                .build()
        ).execute().let {
            if (it.isSuccessful) {
                when (String(it.body!!.bytes())) {
                    "true" -> return@withContext true
                    else -> return@withContext false
                }
            } else {
                return@withContext null
            }
        }

    }
}
