package com.example.myapi_test.domain.usecase

import com.example.myapi_test.domain.model.AuthResult
import com.example.myapi_test.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String): AuthResult {

        return authRepository.login(email, password)
    }
}
