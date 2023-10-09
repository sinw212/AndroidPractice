package com.example.searchimage.domain.repository

import com.example.searchimage.domain.model.ImageDocumentEntity
import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.model.VideoDocumentEntity

interface SearchRepository {
    suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int
    ): SearchEntity<ImageDocumentEntity>

    suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int
    ): SearchEntity<VideoDocumentEntity>
}