package com.example.searchimage.domain.repository

import com.example.searchimage.domain.model.ImageDocumentEntity
import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.model.VideoDocumentEntity
import com.example.searchimage.domain.usecase.GetSearchImageParams
import com.example.searchimage.domain.usecase.GetSearchVideoParams

interface SearchRepository {
    suspend fun getSearchImage(
        params: GetSearchImageParams
    ): SearchEntity<ImageDocumentEntity>

    suspend fun getSearchVideo(
        params: GetSearchVideoParams
    ): SearchEntity<VideoDocumentEntity>
}