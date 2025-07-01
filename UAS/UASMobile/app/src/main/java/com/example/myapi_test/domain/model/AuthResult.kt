package com.example.myapi_test.domain.model

sealed class AuthResult {
    object Success : AuthResult()
    data class Failure(val message: String) : AuthResult()
}
