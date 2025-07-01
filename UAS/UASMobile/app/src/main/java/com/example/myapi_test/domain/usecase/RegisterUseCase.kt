package com.example.myapi_test.domain.usecase

import com.example.myapi_test.domain.model.AuthResult
import com.example.myapi_test.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String): AuthResult {
        return authRepository.register(email, password)
    }
}
