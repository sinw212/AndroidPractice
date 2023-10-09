package com.example.searchimage.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchResponse<T> (
    @SerializedName("meta") val meta: MetaResponse?,
    @SerializedName("documents") val documents: List<T>?
)

data class MetaResponse(
    @SerializedName("is_end") val isEnd: Boolean?,
    @SerializedName("pageable_count") val pageableCount: Int?,
    @SerializedName("total_count") val totalCount: Int?
)

data class ImageDocumentResponse (
    @SerializedName("collection") val collection: String?,
    @SerializedName("datetime") val datetime: Date?,
    @SerializedName("display_sitename") val displaySitename: String?,
    @SerializedName("doc_url") val docUrl: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("width") val width: Int?
)

data class VideoDocumentResponse (
    @SerializedName("author") val author: String?,
    @SerializedName("datetime") val datetime: Date?,
    @SerializedName("play_time") val playTime: Int?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?
)