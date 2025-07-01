package com.example.myapi_test.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myapi_test.domain.model.Book
import com.example.myapi_test.domain.repository.BookRepository

class GetFavoriteBooksUseCase(private val bookRepository: BookRepository) {

    operator fun invoke(userEmail: String): LiveData<List<Book>> {

        return bookRepository.getFavoriteBooks(userEmail)
    }
}
