package com.example.myapi_test.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyInstance {
    private const val BASE_URL = "https://api.potterdb.com/"

    val api: MyService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyService::class.java)
    }
}