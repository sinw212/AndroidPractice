package com.example.searchimage.presentation.search

import com.example.searchimage.presentation.bookmark.BookmarkItem
import java.util.Date

sealed class SearchItem(
    open val imgThumbnail: String?,
    open val date: Date?,
    open val isBookmark: Boolean
) {
    abstract fun toBookmarkItem(): BookmarkItem

    data class ImageItem(
        override val imgThumbnail: String?,
        val txtTitle: String?,
        override val date: Date?,
        override val isBookmark: Boolean
    ) : SearchItem(imgThumbnail, date, isBookmark) {
        override fun toBookmarkItem() = BookmarkItem.ImageItem(
            imgThumbnail = imgThumbnail,
            txtTitle = txtTitle,
            date = date,
            isBookmark = isBookmark
        )
    }

    data class VideoItem(
        override val imgThumbnail: String?,
        val txtTitle: String?,
        override val date: Date?,
        override val isBookmark: Boolean
    ) : SearchItem(imgThumbnail, date, isBookmark) {
        override fun toBookmarkItem() = BookmarkItem.VideoItem(
            imgThumbnail = imgThumbnail,
            txtTitle = txtTitle,
            date = date,
            isBookmark = isBookmark
        )
    }
}