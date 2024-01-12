package com.example.searchimage.domain.usecase

import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.model.VideoDocumentEntity
import com.example.searchimage.domain.repository.SearchRepository

class GetSearchVideoUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(
        params: GetSearchVideoParams
    ): SearchEntity<VideoDocumentEntity> = repository.getSearchVideo(
        params
    )
}