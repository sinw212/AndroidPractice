package com.example.todoapp.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookmarkViewModel: ViewModel() {
    private val _list: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    val list: LiveData<List<BookmarkModel>> get() = _list

    fun addBookmarkItem(
        bookmarkModel: BookmarkModel?
    ) {
        if(bookmarkModel == null) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList.add(bookmarkModel)
        _list.value = currentList
    }

    fun removeBookmarkItem(bookmarkModel: BookmarkModel?) {
        fun findIndex(item: BookmarkModel?): Int {
            val currentList = list.value.orEmpty().toMutableList()
            val findBookmark = currentList.find {
                it.id == item?.id
            }
            return currentList.indexOf(findBookmark)
        }

        if(bookmarkModel == null) {
            return
        }

        val findPosition = findIndex(bookmarkModel)
        if(findPosition < 0) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(findPosition)
        _list.value = currentList
    }
}