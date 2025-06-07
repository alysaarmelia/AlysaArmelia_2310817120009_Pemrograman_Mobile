package com.example.myapi_test.domain.usecase

import com.example.myapi_test.domain.model.Book
import com.example.myapi_test.domain.repository.BookRepository

/**
 * A Use Case that encapsulates the business logic for fetching the list of books.
 * It depends on the BookRepository interface, not its implementation.
 */
class GetBooksUseCase(private val bookRepository: BookRepository) {

    /**
     * Executes the use case. The 'invoke' operator allows this class to be called
     * like a function, e.g., getBooksUseCase().
     */
    suspend operator fun invoke(): Result<List<Book>> {
        return bookRepository.getBooks()
    }
}