package com.example.myapi_test.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapi_test.data.database.entity.BookDbEntity
import com.example.myapi_test.data.database.entity.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: Favorite)

    @Delete
    suspend fun removeFavorite(favorite: Favorite)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE userEmail = :userEmail AND bookTitle = :bookTitle)")
    fun isFavorite(userEmail: String, bookTitle: String): LiveData<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE userEmail = :userEmail AND bookTitle = :bookTitle)")
    fun isFavoriteBlocking(userEmail: String, bookTitle: String): Boolean

    @Query("""
        SELECT b.* FROM books b
        INNER JOIN favorites f ON b.title = f.bookTitle
        WHERE f.userEmail = :userEmail
        ORDER BY b.title ASC
    """)
    fun getFavoriteBooksForUser(userEmail: String): LiveData<List<BookDbEntity>>
}
