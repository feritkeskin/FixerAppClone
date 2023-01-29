package com.feritkeskin.fixerappclone.service

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.feritkeskin.fixerappclone.BuildConfig
import com.feritkeskin.fixerappclone.util.Contains.BASE_URL
import com.feritkeskin.fixerappclone.util.FlipperNetworkObject.networkFlipperPlugin
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {

    fun getUsers(): FixerAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(FixerAPI::class.java)
    }

    private fun client(): OkHttpClient.Builder {
        return if (BuildConfig.DEBUG && networkFlipperPlugin != null) {
            OkHttpClient.Builder()
                .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
        } else {
            OkHttpClient.Builder()
        }
    }
}