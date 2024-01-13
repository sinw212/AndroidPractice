package com.example.searchimage.presentation.bookmark

import com.example.searchimage.presentation.search.SearchItem
import java.util.Date

sealed class BookmarkItem {
    abstract fun toSearchItem(): SearchItem

    data class ImageItem(
        val imgThumbnail: String?,
        val txtTitle: String?,
        val date: Date?,
        val isBookmark: Boolean
    ) : BookmarkItem() {
        override fun toSearchItem() = SearchItem.ImageItem(
            imgThumbnail = imgThumbnail,
            txtTitle = txtTitle,
            date = date,
            isBookmark = isBookmark
        )
    }

    data class VideoItem(
        val imgThumbnail: String?,
        val txtTitle: String?,
        val date: Date?,
        val isBookmark: Boolean
    ) : BookmarkItem() {
        override fun toSearchItem() = SearchItem.VideoItem(
            imgThumbnail = imgThumbnail,
            txtTitle = txtTitle,
            date = date,
            isBookmark = isBookmark
        )
    }
}