package com.example.myapi_test.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * A factory class for creating BookViewModel instances.
 * It's required because our BookViewModel has a constructor with parameters (Application).
 */
class BookViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given `Class`.
     * @param modelClass a `Class` whose instance is requested
     * @return a newly created ViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel class is our BookViewModel
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            // If it is, create and return an instance of it, passing the application context.
            // The unchecked cast is safe because of the isAssignableFrom check.
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(application) as T
        }
        // If it's a different ViewModel, throw an exception.
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}