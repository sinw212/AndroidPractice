package com.example.searchimage.domain.usecase

import com.example.searchimage.domain.model.ImageDocumentEntity
import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.repository.SearchRepository

class GetSearchImageUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(
        params: GetSearchImageParams
    ): SearchEntity<ImageDocumentEntity> = repository.getSearchImage(
        params
    )
}