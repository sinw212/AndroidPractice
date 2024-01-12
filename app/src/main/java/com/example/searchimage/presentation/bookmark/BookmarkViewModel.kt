package com.example.searchimage.presentation.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookmarkViewModel: ViewModel() {
    private val _bookmarkList: MutableLiveData<List<BookmarkItem>> = MutableLiveData()
    val bookmarkList: LiveData<List<BookmarkItem>>
        get() = _bookmarkList

    fun addBookmarkItem(item: BookmarkItem?) {
        if(item == null) {
            return
        }
        val currentList = bookmarkList.value.orEmpty().toMutableList()
        currentList.add(item)
        _bookmarkList.value = currentList
    }

    fun deleteBookmarkItem(item: BookmarkItem?) {
        if(item == null) {
            return
        }
        val currentList = bookmarkList.value.orEmpty().toMutableList()
        currentList.remove(item)
        _bookmarkList.value = currentList
    }

    fun deleteBookmarkItem(position: Int?) {
        if(position == null || position < 0) {
            return
        }
        val currentList = bookmarkList.value.orEmpty().toMutableList()
        currentList.removeAt(position)
        _bookmarkList.value = currentList
    }
}

class BookmarkViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel() as T
        } else {
            throw IllegalArgumentException("Not Found ViewModel Class")
        }
    }
}