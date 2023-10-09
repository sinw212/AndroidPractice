package com.example.searchimage.presentation.search

import java.util.Date

sealed class SearchItem(
    open val date: Date?
) {
    data class ImageItem(
        val imgThumbnail: String?,
        val txtTitle: String?,
        override val date: Date?
    ) : SearchItem(date)

    data class VideoItem(
        val imgThumbnail: String?,
        val txtTitle: String?,
        override val date: Date?
    ) : SearchItem(date)
}