package com.example.myapi_test.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapi_test.data.database.AppDatabase
import com.example.myapi_test.data.database.entity.BookDbEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BookDaoTest {

    private lateinit var bookDao: BookDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        bookDao = db.bookDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetBook() = runBlocking {
        val book = BookDbEntity(
            title = "A Test Book",
            author = "Test Author",
            cover = "cover_url",
            releaseDate = "2025-06-07",
            summary = "A summary.",
            wiki = "wiki_url"
        )
        bookDao.insertBooks(listOf(book))
        val allBooks = bookDao.getAllBooks()
        assertTrue(allBooks.contains(book))
    }

    @Test
    @Throws(Exception::class)
    fun clearAllBooks() = runBlocking {
        val book = BookDbEntity("A Test Book", "Test Author", "", "", "", "")
        bookDao.insertBooks(listOf(book))

        bookDao.clearAllBooks()

        val allBooks = bookDao.getAllBooks()
        assertTrue(allBooks.isEmpty())
    }
}