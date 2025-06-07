package com.example.myapi_test.data.api

// DTOs that exactly match the JSON response from the API.

data class BookApiResponse(
    val data: List<BookData>
)

data class BookData(
    val attributes: BookAttributes
)

data class BookAttributes(
    val author: String,
    val cover: String,
    val release_date: String,
    val title: String,
    val wiki: String,
    val summary: String
)