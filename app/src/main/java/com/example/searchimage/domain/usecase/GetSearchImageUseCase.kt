package com.example.searchimage.domain.usecase

import com.example.searchimage.domain.model.ImageDocumentEntity
import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.repository.SearchRepository

class GetSearchImageUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(
        query: String,
        sort: String = "recency",
        page: Int = 1
    ): SearchEntity<ImageDocumentEntity> = repository.getSearchImage(
        query,
        sort,
        page
    )
}