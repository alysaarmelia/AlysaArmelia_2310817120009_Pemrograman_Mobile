package com.example.myapi_test.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookUi(
    val author: String,
    val cover: String,
    val release_date: String,
    val title: String,
    val wiki: String,
    val summary: String
) : Parcelable