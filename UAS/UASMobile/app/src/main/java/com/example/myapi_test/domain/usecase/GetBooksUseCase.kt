package com.example.myapi_test.domain.usecase

import com.example.myapi_test.domain.model.Book
import com.example.myapi_test.domain.repository.BookRepository

class GetBooksUseCase(private val bookRepository: BookRepository) {

    suspend operator fun invoke(): Result<List<Book>> {
        return bookRepository.getBooks()
    }
}