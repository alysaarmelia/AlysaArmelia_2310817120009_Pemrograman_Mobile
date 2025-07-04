package com.example.myapi_test.data.mappers

import com.example.myapi_test.data.api.BookData
import com.example.myapi_test.domain.model.Book
import com.example.myapi_test.presentation.model.BookUi
import com.example.myapi_test.data.api.BookAttributes
import com.example.myapi_test.data.database.entity.BookDbEntity

fun BookData.toDomain(): Book {
    return Book(
        title = this.attributes.title,
        author = this.attributes.author,
        cover = this.attributes.cover,
        releaseDate = this.attributes.release_date,
        summary = this.attributes.summary,
        wiki = this.attributes.wiki
    )
}
fun Book.toUiModel(): BookUi {
    return BookUi(
        title = this.title,
        author = this.author,
        cover = this.cover,
        release_date = this.releaseDate,
        summary = this.summary,
        wiki = this.wiki
    )
}

fun BookAttributes.toDbEntity(): BookDbEntity {
    return BookDbEntity(
        title = this.title,
        author = this.author,
        cover = this.cover,
        releaseDate = this.release_date,
        summary = this.summary,
        wiki = this.wiki
    )
}

fun BookDbEntity.toDomain(): Book {
    return Book(
        title = this.title,
        author = this.author,
        cover = this.cover,
        releaseDate = this.releaseDate,
        summary = this.summary,
        wiki = this.wiki
    )
}