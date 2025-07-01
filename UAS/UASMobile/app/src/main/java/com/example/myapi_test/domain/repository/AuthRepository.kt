package com.example.myapi_test.domain.repository

import com.example.myapi_test.domain.model.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult

    suspend fun register(email: String, password: String): AuthResult
}