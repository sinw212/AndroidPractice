package com.example.searchimage.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchimage.presentation.bookmark.BookmarkItem
import com.example.searchimage.presentation.search.SearchItem

class MainSharedViewModel: ViewModel() {
    private val _searchEvent: MutableLiveData<MainSharedEventForSearch> = MutableLiveData()
    val searchEvent: LiveData<MainSharedEventForSearch>
        get() = _searchEvent

    private val _bookmarkEvent: MutableLiveData<MainSharedEventForBookmark> = MutableLiveData()
    val bookmarkEvent: LiveData<MainSharedEventForBookmark>
        get() = _bookmarkEvent

    fun updateBookmarkItem(item: SearchItem?) {
        if (item == null) {
            return
        }
        _bookmarkEvent.value =
            if (item.isBookmark)
                MainSharedEventForBookmark.AddBookmarkItem(item.toBookmarkItem())
            else
                MainSharedEventForBookmark.RemoveBookmarkItem(item.toBookmarkItem())
    }

    fun updateSearchItem(item: BookmarkItem) {
        _searchEvent.value = MainSharedEventForSearch.UpdateSearchItem(item.toSearchItem())
    }
}

sealed interface MainSharedEventForSearch {
    data class UpdateSearchItem(
        val item: SearchItem
    ): MainSharedEventForSearch
}

sealed interface MainSharedEventForBookmark {
    data class AddBookmarkItem(
        val item: BookmarkItem
    ): MainSharedEventForBookmark

    data class RemoveBookmarkItem(
        val item: BookmarkItem
    ): MainSharedEventForBookmark
}