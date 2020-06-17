package com.valeriu.colindandroid.data.source.remote

import com.valeriu.colindandroid.data.models.ColindDto
import retrofit2.Call
import retrofit2.http.GET

interface ColindApi {
    @GET("api/colind")
    suspend fun getColindList(): List<ColindDto>


}