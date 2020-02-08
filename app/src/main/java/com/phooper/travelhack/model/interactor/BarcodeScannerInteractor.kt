package com.phooper.travelhack.model.interactor

import com.phooper.travelhack.App
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.create
import java.io.File
import javax.inject.Inject


class BarcodeScannerInteractor {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var okhttpClient: OkHttpClient

    suspend fun validateOLD(code: String) = withContext(IO) {
        listOf(
            "1500000000042",
            "1500000000080",
            "1500000000097",
            "1500000000035",
            "1500000000028",
            "1500000000011",
            "1500000000004",
            "1500000000103",
            "1500000000110"
        ).contains(code)
    }

    suspend fun validate() {
        val request: Request = Request.Builder()
            .url("localhost:8080/api/photos")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build()
        val response: Response = okhttpClient.newCall(request).execute()

    }

//TODO методы которые нужны:
//void insertNewClient(String barcode) - чтобы я мог добавлять новых
//Boolean validate(String barcode)  - проверка на валидность
//void markAsUsed(String barcode) - сделать билет невалидным

}
