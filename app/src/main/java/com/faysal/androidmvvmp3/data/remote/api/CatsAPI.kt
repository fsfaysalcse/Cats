package com.faysal.androidmvvmp3.data.remote.api

import com.faysal.androidmvvmp3.models.Cats
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsAPI {
    @GET("v1/sources")
    suspend fun getAllCats(
        @Query("limit") limit : Int,
        @Query("page") page : Int,
    ) : Cats

}