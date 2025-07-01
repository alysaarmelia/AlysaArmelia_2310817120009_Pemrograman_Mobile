package com.example.myapi_test.data.repository

import android.content.Context
import com.example.myapi_test.data.database.AppDatabase
import com.example.myapi_test.data.database.entity.UserDbEntity
import com.example.myapi_test.data.util.PasswordHasher
import com.example.myapi_test.data.util.SessionManager
import com.example.myapi_test.domain.model.AuthResult
import com.example.myapi_test.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(private val context: Context) : AuthRepository {

    private val userDao = AppDatabase.getDatabase(context).userDao()

    override suspend fun login(email: String, password: String): AuthResult {
        return withContext(Dispatchers.IO) {
            val user = userDao.findUserByEmail(email)
                ?: return@withContext AuthResult.Failure("User not found. Please check your email.")

            val providedPasswordHash = PasswordHasher.hashPassword(password)
            val isPasswordCorrect = user.passwordHash == providedPasswordHash

            if (isPasswordCorrect) {
                SessionManager.saveSession(context, email)
                AuthResult.Success
            } else {
                AuthResult.Failure("Invalid password. Please try again.")
            }
        }
    }

    override suspend fun register(email: String, password: String): AuthResult {
        return withContext(Dispatchers.IO) {
            if (userDao.findUserByEmail(email) != null) {
                return@withContext AuthResult.Failure("A user with this email already exists.")
            }
            val passwordHash = PasswordHasher.hashPassword(password)
            val newUser = UserDbEntity(email = email, passwordHash = passwordHash)
            userDao.insertUser(newUser)
            AuthResult.Success
        }
    }
}
