package com.example.myapi_test.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookDbEntity(
    @PrimaryKey
    val title: String,
    val author: String,
    val cover: String,
    val releaseDate: String,
    val summary: String,
    val wiki: String
)