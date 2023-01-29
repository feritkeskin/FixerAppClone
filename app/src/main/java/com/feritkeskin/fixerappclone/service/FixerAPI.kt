package com.feritkeskin.fixerappclone.service

import com.feritkeskin.fixerappclone.model.FixerModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FixerAPI {

    @GET("/fixer/fluctuation")
    suspend fun getFixer(
        @QueryMap map: HashMap<String, String>
    ): FixerModel
}