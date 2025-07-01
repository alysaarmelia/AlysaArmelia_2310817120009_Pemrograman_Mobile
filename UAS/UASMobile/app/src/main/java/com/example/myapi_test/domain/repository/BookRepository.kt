package com.example.myapi_test.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapi_test.domain.model.Book

interface BookRepository {
    suspend fun getBooks(): Result<List<Book>>

    fun getFavoriteBooks(userEmail: String): LiveData<List<Book>>

    fun isFavorite(userEmail: String, bookTitle: String): LiveData<Boolean>

    suspend fun toggleFavorite(userEmail: String, bookTitle: String)
}
