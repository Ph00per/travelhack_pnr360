package com.phooper.travelhack.model.interactor

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class BarcodeScannerInteractor {

    suspend fun validate(code: String) = withContext(IO) {
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


//TODO методы которые нужны:
//void insertNewClient(String barcode) - чтобы я мог добавлять новых
//Boolean validate(String barcode)  - проверка на валидность
//void markAsUsed(String barcode) - сделать билет невалидным

}
