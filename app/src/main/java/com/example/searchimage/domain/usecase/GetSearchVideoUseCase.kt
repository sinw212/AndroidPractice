package com.example.searchimage.domain.usecase

import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.model.VideoDocumentEntity
import com.example.searchimage.domain.repository.SearchRepository

class GetSearchVideoUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(
        query: String,
        sort: String = "recency",
        page: Int = 1
    ): SearchEntity<VideoDocumentEntity> = repository.getSearchVideo(
        query,
        sort,
        page
    )
}