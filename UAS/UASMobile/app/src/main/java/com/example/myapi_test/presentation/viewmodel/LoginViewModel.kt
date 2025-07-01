package com.example.myapi_test.presentation.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapi_test.data.repository.AuthRepositoryImpl
import com.example.myapi_test.domain.model.AuthResult
import com.example.myapi_test.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    sealed class LoginState {
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    private val authRepository = AuthRepositoryImpl(application)
    private val loginUseCase = LoginUseCase(authRepository)

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error("Email and password cannot be empty.")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _loginState.value = LoginState.Error("Please enter a valid email address.")
            return
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            _loginState.value = LoginState.Error("Password must be at least $MIN_PASSWORD_LENGTH characters long.")
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            when (val result = loginUseCase(email, password)) {
                is AuthResult.Success -> {
                    _loginState.value = LoginState.Success
                }
                is AuthResult.Failure -> {
                    _loginState.value = LoginState.Error(result.message)
                }
            }
        }
    }
}