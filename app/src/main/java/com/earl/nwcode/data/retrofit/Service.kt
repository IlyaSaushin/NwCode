package com.earl.nwcode.data.retrofit

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("api/")
    suspend fun fetchImagesForCategory(
        @Query("key") key: String,
        @Query("category") category: String
    ) : ResponseBody
}