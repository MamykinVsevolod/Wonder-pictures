package com.iu6_mamykin.wonderpicturesclient.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApi {
    @GET("/get-picture")
    suspend fun getPicture(@Query("theme") theme: String): PictureResponse
}

fun createApi(): PictureApi {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // Локальный сервер
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(PictureApi::class.java)
}