package com.example.searchimage.domain.model

import com.example.searchimage.data.model.ImageDocumentResponse
import com.example.searchimage.data.model.MetaResponse
import com.example.searchimage.data.model.SearchResponse
import com.example.searchimage.data.model.VideoDocumentResponse

fun SearchResponse<ImageDocumentResponse>.toImageEntity() = SearchEntity<ImageDocumentEntity>(
    meta = meta?.toEntity(),
    documents = documents?.map {
        it.toEntity()
    }
)

fun SearchResponse<VideoDocumentResponse>.toVideoEntity() = SearchEntity<VideoDocumentEntity>(
    meta = meta?.toEntity(),
    documents = documents?.map {
        it.toEntity()
    }
)

fun MetaResponse.toEntity() = MetaEntity(
    isEnd = isEnd,
    pageableCount = pageableCount,
    totalCount = totalCount
)

fun ImageDocumentResponse.toEntity() = ImageDocumentEntity(
    collection = collection,
    datetime = datetime,
    displaySitename = displaySitename,
    docUrl = docUrl,
    height = height,
    imageUrl = imageUrl,
    thumbnailUrl = thumbnailUrl,
    width = width
)

fun VideoDocumentResponse.toEntity() = VideoDocumentEntity(
    author = author,
    datetime = datetime,
    playTime = playTime,
    thumbnail = thumbnail,
    title = title,
    url = url
)