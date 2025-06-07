package com.example.myapi_test.presentation.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapi_test.data.mappers.toUiModel
import com.example.myapi_test.data.repository.BookRepositoryImpl
import com.example.myapi_test.domain.usecase.GetBooksUseCase
import com.example.myapi_test.presentation.model.BookUi
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    // --- Dependencies ---
    // In a real app, use Dependency Injection (e.g., Hilt) to provide these dependencies.
    // We manually create the instances here for simplicity.
    private val bookRepository = BookRepositoryImpl(application.applicationContext)
    private val getBooksUseCase = GetBooksUseCase(bookRepository)

    // --- LiveData ---
    private val _books = MutableLiveData<List<BookUi>>()
    val booksLiveData: LiveData<List<BookUi>> get() = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorMessage

    private val TAG = "BookViewModel"

    /**
     * Fetches the list of books by executing the use case.
     */
    fun fetchBooks() {
        _isLoading.value = true
        viewModelScope.launch {
            // Execute the use case to get the result
            val result = getBooksUseCase()

            // Handle the result: onSuccess or onFailure
            result.onSuccess { domainBooks ->
                // Map domain models to UI models before posting to LiveData
                _books.postValue(domainBooks.map { it.toUiModel() })
                Log.d(TAG, "Successfully fetched ${domainBooks.size} books.")
            }.onFailure { exception ->
                _errorMessage.postValue("Failed to fetch books: ${exception.message}")
                Log.e(TAG, "Error fetching books", exception)
            }
            // Update loading state regardless of outcome
            _isLoading.postValue(false)
        }
    }
}