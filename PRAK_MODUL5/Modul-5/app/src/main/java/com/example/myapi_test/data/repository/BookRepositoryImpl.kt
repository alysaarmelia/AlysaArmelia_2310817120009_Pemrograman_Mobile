package com.example.myapi_test.data.repository

import android.content.Context
import android.util.Log
import com.example.myapi_test.data.api.MyInstance
import com.example.myapi_test.data.database.AppDatabase
import com.example.myapi_test.data.mappers.toDbEntity
import com.example.myapi_test.data.mappers.toDomain
import com.example.myapi_test.domain.model.Book
import com.example.myapi_test.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implements the BookRepository. It now manages two data sources:
 * 1. Remote: MyInstance (Retrofit API)
 * 2. Local: AppDatabase (Room DAO)
 */
class BookRepositoryImpl(context: Context) : BookRepository {

    private val apiService = MyInstance.api
    private val bookDao = AppDatabase.getDatabase(context).bookDao()

    override suspend fun getBooks(): Result<List<Book>> {
        return withContext(Dispatchers.IO) {
            try {
                // 1. Fetch fresh data from the API
                Log.d("BookRepository", "Fetching books from API...")
                val remoteBooks = apiService.getMessage().data

                // 2. Clear old data from the database
                bookDao.clearAllBooks()

                // 3. Map network DTOs to database entities and insert them
                val dbEntities = remoteBooks.map { it.attributes.toDbEntity() }
                bookDao.insertBooks(dbEntities)
                Log.d("BookRepository", "Successfully inserted ${dbEntities.size} books into database.")

            } catch (e: Exception) {
                // 4. If the network call fails, log the error.
                // The function will proceed to load data from the cache.
                Log.e("BookRepository", "Failed to fetch from API. Loading from cache.", e)
            }

            // 5. Always return data from the database (Single Source of Truth)
            // If the network call succeeded, this is the fresh data.
            // If it failed, this is the old, cached data.
            try {
                val cachedBooks = bookDao.getAllBooks()
                Log.d("BookRepository", "Loaded ${cachedBooks.size} books from database.")
                Result.success(cachedBooks.map { it.toDomain() })
            } catch (e: Exception) {
                Log.e("BookRepository", "Failed to read from database.", e)
                Result.failure(e)
            }
        }
    }
}