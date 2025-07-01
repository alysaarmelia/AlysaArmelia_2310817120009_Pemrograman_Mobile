package com.example.myapi_test.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapi_test.data.database.dao.BookDao
import com.example.myapi_test.data.database.dao.FavoriteDao
import com.example.myapi_test.data.database.dao.UserDao
import com.example.myapi_test.data.database.entity.BookDbEntity
import com.example.myapi_test.data.database.entity.Favorite
import com.example.myapi_test.data.database.entity.UserDbEntity
import com.example.myapi_test.data.util.PasswordHasher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [BookDbEntity::class, UserDbEntity::class, Favorite::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseSeeder(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseSeeder(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                getDatabase(context).userDao().insertUser(
                    UserDbEntity(
                        email = "user@example.com",
                        passwordHash = PasswordHasher.hashPassword("password123")
                    )
                )
            }
        }
    }
}
