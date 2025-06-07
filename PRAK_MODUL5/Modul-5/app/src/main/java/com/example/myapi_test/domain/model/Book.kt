package com.example.myapi_test.domain.model

/**
 * Represents the core business object (Entity). It is a pure data class,
 * independent of any data source or UI implementation details.
 */
data class Book(
    val title: String,
    val author: String,
    val cover: String,
    val releaseDate: String,
    val summary: String,
    val wiki: String
)