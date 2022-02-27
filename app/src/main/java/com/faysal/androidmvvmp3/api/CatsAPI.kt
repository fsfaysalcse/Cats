package com.faysal.androidmvvmp3.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CatsAPI {
    @GET("v1/sources")
    suspend fun getAllCats(
        @Query("limit") limit : Int,
        @Query("page") page : Int,
    )

}