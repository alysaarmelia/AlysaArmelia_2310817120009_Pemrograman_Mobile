package com.example.myapi_test.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.myapi_test.data.api.MyInstance
import com.example.myapi_test.data.database.AppDatabase
import com.example.myapi_test.data.database.entity.Favorite
import com.example.myapi_test.data.mappers.toDbEntity
import com.example.myapi_test.data.mappers.toDomain
import com.example.myapi_test.domain.model.Book
import com.example.myapi_test.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepositoryImpl(context: Context) : BookRepository {

    private val apiService = MyInstance.api
    private val appDatabase = AppDatabase.getDatabase(context)
    private val bookDao = appDatabase.bookDao()
    private val favoriteDao = appDatabase.favoriteDao()

    override suspend fun getBooks(): Result<List<Book>> {
        return withContext(Dispatchers.IO) {
            try {
                val remoteBooks = apiService.getMessage().data
                val dbEntities = remoteBooks.map { it.attributes.toDbEntity() }
                bookDao.insertBooks(dbEntities)
            } catch (e: Exception) {
                Log.e("BookRepository", "Failed to fetch from API. Loading from cache.", e)
            }

            try {
                val cachedBooks = bookDao.getAllBooks()
                Result.success(cachedBooks.map { it.toDomain() })
            } catch (e: Exception) {
                Log.e("BookRepository", "Failed to read from database.", e)
                Result.failure(e)
            }
        }
    }

    override fun getFavoriteBooks(userEmail: String): LiveData<List<Book>> {
        return favoriteDao.getFavoriteBooksForUser(userEmail).map { dbEntities ->
            dbEntities.map { it.toDomain() }
        }
    }

    override fun isFavorite(userEmail: String, bookTitle: String): LiveData<Boolean> {
        return favoriteDao.isFavorite(userEmail, bookTitle)
    }

    override suspend fun toggleFavorite(userEmail: String, bookTitle: String) {
        withContext(Dispatchers.IO) {
            val isCurrentlyFavorite = favoriteDao.isFavoriteBlocking(userEmail, bookTitle)
            val favorite = Favorite(userEmail = userEmail, bookTitle = bookTitle)

            if (isCurrentlyFavorite) {
                favoriteDao.removeFavorite(favorite)
            } else {
                favoriteDao.addFavorite(favorite)
            }
        }
    }
}