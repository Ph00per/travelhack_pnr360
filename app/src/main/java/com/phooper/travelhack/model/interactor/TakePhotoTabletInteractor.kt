package com.phooper.travelhack.model.interactor

import android.util.Log
import com.phooper.travelhack.App
import com.phooper.travelhack.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class TakePhotoTabletInteractor {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var okhttpClient: OkHttpClient

    suspend fun setPhotoState(barcode: String) = withContext(IO) {
        try {
            okhttpClient.newCall(
                Request.Builder()
                    .url("${Constants.BASE_URI}api/users/que?barcode=$barcode").method(
                        "POST",
                        "".toRequestBody()
                    )
                    .build()
            ).execute()
        } catch (e: Exception) {
            Log.d("Error", e.localizedMessage)
        }
    }
}
