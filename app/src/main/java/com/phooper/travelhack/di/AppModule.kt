package com.phooper.travelhack.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.phooper.travelhack.R
import com.phooper.travelhack.model.interactor.BarcodeScannerInteractor
import com.phooper.travelhack.model.interactor.EditPrintPhotoInteractor
import dagger.Module
import dagger.Provides
import dev.dinoparty.MysqlConnector
import dev.dinoparty.PhotoSession
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideBarcodeInteractor() = BarcodeScannerInteractor()

    @Provides
    @Singleton
    fun provideEditPrintInteractor() = EditPrintPhotoInteractor()

    @Provides
    @Singleton
    fun provideRequestOptions(): RequestOptions =
        RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

    @Provides
    @Singleton
    fun provideGlideInstance(
        context: Context, requestOptions: RequestOptions
    ): RequestManager =
        Glide.with(context)
            .setDefaultRequestOptions(requestOptions)

}