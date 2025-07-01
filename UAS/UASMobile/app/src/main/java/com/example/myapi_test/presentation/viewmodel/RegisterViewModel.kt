package com.example.myapi_test.presentation.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapi_test.data.repository.AuthRepositoryImpl
import com.example.myapi_test.domain.model.AuthResult
import com.example.myapi_test.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    sealed class RegisterState {
        object Loading : RegisterState()
        object Success : RegisterState()
        data class Error(val message: String) : RegisterState()
    }

    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> get() = _registerState

    private val authRepository = AuthRepositoryImpl(application)
    private val registerUseCase = RegisterUseCase(authRepository)

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    fun register(email: String, password: String, confirmPassword: String) {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _registerState.value = RegisterState.Error("All fields must be filled.")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _registerState.value = RegisterState.Error("Please enter a valid email address.")
            return
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            _registerState.value = RegisterState.Error("Password must be at least $MIN_PASSWORD_LENGTH characters long.")
            return
        }

        if (password != confirmPassword) {
            _registerState.value = RegisterState.Error("Passwords do not match.")
            return
        }

        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            when (val result = registerUseCase(email, password)) {
                is AuthResult.Success -> {
                    _registerState.value = RegisterState.Success
                }
                is AuthResult.Failure -> {
                    _registerState.value = RegisterState.Error(result.message)
                }
            }
        }
    }
}
