package com.example.myapi_test.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapi_test.data.database.dao.BookDao
import com.example.myapi_test.data.database.entity.BookDbEntity

@Database(entities = [BookDbEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        // Volatile ensures that the INSTANCE is always up-to-date and the same for all execution threads.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Return the existing instance if it exists, otherwise create a new one.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book_database"
                )
                    .fallbackToDestructiveMigration() // Strategy for handling version changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}