package com.example.myapi_test.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapi_test.data.database.entity.BookDbEntity

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookDbEntity>)

    @Query("SELECT * FROM books ORDER BY title ASC")
    suspend fun getAllBooks(): List<BookDbEntity>

    @Query("DELETE FROM books")
    suspend fun clearAllBooks()
}