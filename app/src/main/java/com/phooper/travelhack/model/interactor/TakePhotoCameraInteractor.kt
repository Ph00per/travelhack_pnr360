package com.phooper.travelhack.model.interactor

import android.util.Log
import com.phooper.travelhack.App
import com.phooper.travelhack.utils.Constants
import com.phooper.travelhack.utils.Constants.Companion.BASE_URI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class TakePhotoCameraInteractor {
    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var okhttpClient: OkHttpClient

    suspend fun getCurrentPhotoFlag(): String? = withContext(IO) {
        try {
            okhttpClient.newCall(
                Request.Builder()
                    .url(Constants.BASE_URI + "api/users/que")
                    .build()
            ).execute().let {
                if (it.isSuccessful) {
                    return@withContext String(it.body!!.bytes())

                } else {
                    return@withContext null
                }
            }
        } catch (e: Exception) {
            Log.d("ERROR", e.message)
            return@withContext null
        }
    }

    suspend fun clearFlag(barcode: String) = withContext(IO) {
        try {
            okhttpClient.newCall(
                Request.Builder()
                    .url("${Constants.BASE_URI}api/users/que?barcode=$barcode").method(
                        "DELETE",
                        "".toRequestBody()
                    )
                    .build()
            ).execute()
        } catch (e: Exception) {
            Log.d("Error", e.localizedMessage)
        }
    }

    suspend fun sendPhoto(barcode: String, img: ByteArray) = withContext(IO) {
        try {
            val body =
                okhttpClient.newCall(
                    Request.Builder().url(BASE_URI + "api/photos?barcode=$barcode").post(
                        MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart(
                                "photo", barcode + "pic"
                                , RequestBody.create("image/png".toMediaType(), img)
                            )
                            .addFormDataPart("barcode", barcode).build()
                    )
                        .build()
                ).execute().let {
                    Log.d("load img is succes", it.isSuccessful.toString())
                    Log.d("body", String(it.body!!.bytes()))
                }
        } catch (e: Exception) {
            Log.d("ERROR", e.message)
        }
    }
}
