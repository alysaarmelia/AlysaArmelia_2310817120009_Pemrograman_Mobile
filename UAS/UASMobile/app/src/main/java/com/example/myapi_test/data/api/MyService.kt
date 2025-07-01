package com.example.myapi_test.data.api

import retrofit2.http.GET

interface MyService {
    @GET("v1/books")
    suspend fun getMessage(): BookApiResponse
}