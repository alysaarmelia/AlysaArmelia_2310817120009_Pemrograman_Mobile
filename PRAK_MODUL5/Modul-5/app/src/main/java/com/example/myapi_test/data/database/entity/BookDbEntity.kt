package com.example.myapi_test.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines the schema for the 'books' table in the Room database.
 * Each instance of this class represents a row in the table.
 */
@Entity(tableName = "books")
data class BookDbEntity(
    // We use the title as the primary key, assuming it's unique.
    // For real-world apps, a unique ID from the API is preferable.
    @PrimaryKey
    val title: String,
    val author: String,
    val cover: String,
    val releaseDate: String,
    val summary: String,
    val wiki: String
)