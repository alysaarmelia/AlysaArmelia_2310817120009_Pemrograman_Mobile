package com.example.myapi_test.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDbEntity(
    @PrimaryKey
    val email: String,
    val passwordHash: String
)