package com.phooper.travelhack.model.interactor

import android.util.Log
import com.phooper.travelhack.App
import com.phooper.travelhack.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.redisson.api.RBucket
import org.redisson.api.RedissonClient
import javax.inject.Inject

class TakePhotoTabletInteractor {

    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var redissonClient: RedissonClient

    val bucket: RBucket<String> = redissonClient.getBucket(
        Constants.PHOTO_FLAG,
        org.redisson.client.codec.StringCodec("utf-8")
    )

    suspend fun setPhotoState(barcode: String) = withContext(IO) {
        try {
            bucket.set(barcode)
            Log.d("New Bucket", bucket.get())
        } catch (e: Exception) {
            Log.d("Error", e.localizedMessage)
        }
    }

}