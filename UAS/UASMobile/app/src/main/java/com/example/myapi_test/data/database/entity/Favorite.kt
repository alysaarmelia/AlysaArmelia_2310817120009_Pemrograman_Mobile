package com.example.myapi_test.data.database.entity

import androidx.room.Entity

@Entity(tableName = "favorites", primaryKeys = ["userEmail", "bookTitle"])
data class Favorite(
    val userEmail: String,
    val bookTitle: String
)