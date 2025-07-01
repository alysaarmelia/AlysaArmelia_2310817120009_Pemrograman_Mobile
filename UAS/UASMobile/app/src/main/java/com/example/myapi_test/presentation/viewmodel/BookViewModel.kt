package com.example.myapi_test.presentation.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapi_test.data.mappers.toUiModel
import com.example.myapi_test.data.repository.BookRepositoryImpl
import com.example.myapi_test.domain.usecase.GetBooksUseCase
import com.example.myapi_test.presentation.model.BookUi
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val bookRepository = BookRepositoryImpl(application.applicationContext)
    private val getBooksUseCase = GetBooksUseCase(bookRepository)

    private val _books = MutableLiveData<List<BookUi>>()
    val booksLiveData: LiveData<List<BookUi>> get() = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorMessage

    private val TAG = "BookViewModel"

    fun fetchBooks() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getBooksUseCase()

            result.onSuccess { domainBooks ->
                _books.postValue(domainBooks.map { it.toUiModel() })
                Log.d(TAG, "Successfully fetched ${domainBooks.size} books.")
            }.onFailure { exception ->
                _errorMessage.postValue("Failed to fetch books: ${exception.message}")
                Log.e(TAG, "Error fetching books", exception)
            }
            _isLoading.postValue(false)
        }
    }
}