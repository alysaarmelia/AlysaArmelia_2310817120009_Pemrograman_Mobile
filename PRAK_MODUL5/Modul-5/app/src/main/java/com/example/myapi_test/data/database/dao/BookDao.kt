package com.example.myapi_test.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapi_test.data.database.entity.BookDbEntity

@Dao
interface BookDao {
    /**
     * Inserts a list of books into the database. If a book with the same
     * primary key already exists, it will be replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookDbEntity>)

    /**
     * Retrieves all books from the database, ordered by title.
     * @return A list of all BookDbEntity objects.
     */
    @Query("SELECT * FROM books ORDER BY title ASC")
    suspend fun getAllBooks(): List<BookDbEntity>

    /**
     * Deletes all books from the database.
     */
    @Query("DELETE FROM books")
    suspend fun clearAllBooks()
}