package com.phooper.travelhack.model.interactor

import android.util.Log
import com.phooper.travelhack.App
import com.phooper.travelhack.utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.redisson.api.RBucket
import org.redisson.api.RedissonClient
import javax.inject.Inject

class TakePhotoCameraInteractor {
    init {
        App.daggerComponent.inject(this)
    }

    @Inject
    lateinit var redissonClient: RedissonClient

    private val bucket: RBucket<String> = redissonClient.getBucket<String>(
        Constants.photo_flag,
        org.redisson.client.codec.StringCodec("utf-8")
    )

    suspend fun getCurrentPhotoFlag(): String? = withContext(IO) {
        try {
            return@withContext if (bucket.get().isNullOrEmpty()) {
                Log.d("Err", "bucket is null")
                null
            } else {
                Log.d("GOt bucket", bucket.get())
                return@withContext bucket.andDelete
            }
        } catch (e: Exception) {
            Log.d("Error", e.stackTrace.toString())
            return@withContext null
        }
    }


}