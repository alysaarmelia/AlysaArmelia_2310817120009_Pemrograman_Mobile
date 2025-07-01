package com.example.myapi_test.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.myapi_test.data.mappers.toUiModel
import com.example.myapi_test.data.repository.BookRepositoryImpl
import com.example.myapi_test.data.util.SessionManager
import com.example.myapi_test.domain.usecase.GetFavoriteBooksUseCase
import com.example.myapi_test.presentation.model.BookUi

class FavoriteBookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository = BookRepositoryImpl(application)
    private val getFavoriteBooksUseCase = GetFavoriteBooksUseCase(bookRepository)

    val favoriteBooks: LiveData<List<BookUi>> =
        getFavoriteBooksUseCase(SessionManager.getCurrentUserEmail(application) ?: "").map { domainBooks ->
            domainBooks.map { it.toUiModel() }
        }
}
