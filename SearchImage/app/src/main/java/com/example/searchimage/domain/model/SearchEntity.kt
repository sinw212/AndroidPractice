package com.example.searchimage.domain.model

import java.util.Date

data class SearchEntity<T>(
    val meta: MetaEntity?,
    val documents: List<T>?
)

data class MetaEntity(
    val isEnd: Boolean?,
    val pageableCount: Int?,
    val totalCount: Int?
)

data class ImageDocumentEntity(
    val collection: String?,
    val datetime: Date?,
    val displaySitename: String?,
    val docUrl: String?,
    val height: Int?,
    val imageUrl: String?,
    val thumbnailUrl: String?,
    val width: Int?
)

data class VideoDocumentEntity(
    val author: String?,
    val datetime: Date?,
    val playTime: Int?,
    val thumbnail: String?,
    val title: String?,
    val url: String?
)