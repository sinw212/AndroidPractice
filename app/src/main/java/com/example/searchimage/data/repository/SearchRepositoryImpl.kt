package com.example.searchimage.data.repository

import com.example.searchimage.domain.repository.SearchRepository
import com.example.searchimage.data.remote.SearchRemoteDatasource
import com.example.searchimage.domain.model.ImageDocumentEntity
import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.model.VideoDocumentEntity
import com.example.searchimage.domain.model.toImageEntity
import com.example.searchimage.domain.model.toVideoEntity

class SearchRepositoryImpl(
    private val remoteDatasource: SearchRemoteDatasource
) : SearchRepository {
    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int
    ): SearchEntity<ImageDocumentEntity> =
        remoteDatasource.getSearchImage(
            query = query,
            sort = sort,
            page = page
        ).toImageEntity()

    override suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int
    ): SearchEntity<VideoDocumentEntity> =
        remoteDatasource.getSearchVideo(
            query = query,
            sort = sort,
            page = page
        ).toVideoEntity()
}