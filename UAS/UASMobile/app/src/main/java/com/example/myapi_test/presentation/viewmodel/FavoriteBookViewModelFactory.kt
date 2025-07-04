package com.example.myapi_test.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteBookViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteBookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteBookViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
