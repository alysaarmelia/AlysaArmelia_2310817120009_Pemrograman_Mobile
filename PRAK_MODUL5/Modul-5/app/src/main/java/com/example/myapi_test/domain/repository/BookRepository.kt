package com.example.myapi_test.domain.repository

import com.example.myapi_test.domain.model.Book

/**
 * Defines a contract for data operations that the Data layer must implement.
 * The domain layer uses this interface to access data, keeping it decoupled
 * from the data source's implementation (e.g., Retrofit, Room).
 */
interface BookRepository {
    /**
     * Fetches a list of books from the data source.
     * @return A Result wrapper containing either the list of books on success or an exception on failure.
     */
    suspend fun getBooks(): Result<List<Book>>
}