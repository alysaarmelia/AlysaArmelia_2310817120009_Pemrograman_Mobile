package com.example.myapi_test.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapi_test.data.repository.BookRepositoryImpl
import com.example.myapi_test.data.util.SessionManager
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository = BookRepositoryImpl(application)
    private val userEmail = SessionManager.getCurrentUserEmail(application)

    fun isFavorite(bookTitle: String): LiveData<Boolean> {
        return bookRepository.isFavorite(userEmail ?: "", bookTitle)
    }

    fun toggleFavoriteStatus(bookTitle: String) {
        userEmail?.let { email ->
            viewModelScope.launch {
                bookRepository.toggleFavorite(email, bookTitle)
            }
        }
    }
}