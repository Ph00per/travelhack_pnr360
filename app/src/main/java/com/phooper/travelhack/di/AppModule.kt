package com.phooper.travelhack.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.phooper.travelhack.R
import com.phooper.travelhack.model.interactor.BarcodeScannerInteractor
import com.phooper.travelhack.model.interactor.TakePhotoCameraInteractor
import com.phooper.travelhack.model.interactor.TakePhotoTabletInteractor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
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
    fun providePhotoTabletInteractor() = TakePhotoTabletInteractor()

    @Provides
    @Singleton
    fun provideCameraInteractor() = TakePhotoCameraInteractor()

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

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .build()
}