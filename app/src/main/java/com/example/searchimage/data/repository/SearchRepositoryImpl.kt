package com.example.searchimage.data.repository

import com.example.searchimage.domain.repository.SearchRepository
import com.example.searchimage.data.remote.SearchRemoteDatasource
import com.example.searchimage.domain.model.ImageDocumentEntity
import com.example.searchimage.domain.model.SearchEntity
import com.example.searchimage.domain.model.VideoDocumentEntity
import com.example.searchimage.domain.model.toImageEntity
import com.example.searchimage.domain.model.toVideoEntity
import com.example.searchimage.domain.usecase.GetSearchImageParams
import com.example.searchimage.domain.usecase.GetSearchVideoParams

class SearchRepositoryImpl(
    private val remoteDatasource: SearchRemoteDatasource
) : SearchRepository {
    override suspend fun getSearchImage(
        params: GetSearchImageParams
    ): SearchEntity<ImageDocumentEntity> =
        remoteDatasource.getSearchImage(
            query = params.query,
            sort = params.sort,
            page = params.page
        ).toImageEntity()

    override suspend fun getSearchVideo(
        params: GetSearchVideoParams
    ): SearchEntity<VideoDocumentEntity> =
        remoteDatasource.getSearchVideo(
            query = params.query,
            sort = params.sort,
            page = params.page
        ).toVideoEntity()
}