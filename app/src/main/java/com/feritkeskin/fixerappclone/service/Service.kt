package com.feritkeskin.fixerappclone.service

import com.feritkeskin.fixerappclone.util.Contains.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {

    fun getUsers(): FixerAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(FixerAPI::class.java)
    }
}