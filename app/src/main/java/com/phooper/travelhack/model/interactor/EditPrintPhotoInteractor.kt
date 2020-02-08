package com.phooper.travelhack.model.interactor

import com.phooper.travelhack.R
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class EditPrintPhotoInteractor {

    private suspend fun getImagesByBarcode(barcode: String) = withContext(coroutineContext) {
        //TODO search imgs
        listOf(
            R.drawable.sample_chroma_1,
            R.drawable.sample_chroma_2,
            R.drawable.sample_chroma_3
        )
    }

}