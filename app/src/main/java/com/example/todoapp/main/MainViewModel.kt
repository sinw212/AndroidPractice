package com.example.todoapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.bookmark.BookmarkModel
import com.example.todoapp.todo.home.TodoModel

class MainViewModel: ViewModel() {
    private val _todoList: MutableLiveData<List<TodoModel>> = MutableLiveData()
    val todoList: LiveData<List<TodoModel>> get() = _todoList

    private val _bookmarkList: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    val bookmarkList: LiveData<List<BookmarkModel>> get() = _bookmarkList

    fun modifyTodoList(todoList: List<TodoModel>) {
        _todoList.value = todoList
    }

    fun modifyTodoItem(todoModel: TodoModel?) {
        fun findIndex(item: TodoModel?): Int {
            val currentTodoList = todoList.value.orEmpty().toMutableList()
            val findTodo = currentTodoList.find {
                it.id == item?.id
            }
            return currentTodoList.indexOf(findTodo)
        }

        if(todoModel == null) {
            return
        }

        val findPosition = findIndex(todoModel)
        if(findPosition < 0) {
            return
        }

        val currentTodoList = todoList.value.orEmpty().toMutableList()

        currentTodoList[findPosition] = todoModel
        _todoList.value = currentTodoList
    }

    fun modifyBookmarkList(bookmarkList: List<BookmarkModel>) {
        _bookmarkList.value = bookmarkList
    }

    fun modifyBookmarkItem(bookmarkModel: BookmarkModel?) {
        fun addBookmarkItem(bookmarkModel: BookmarkModel) {
            val currentBookmarkList = bookmarkList.value.orEmpty().toMutableList()
            currentBookmarkList.add(bookmarkModel)
            _bookmarkList.value = currentBookmarkList
        }

        fun removeBookmarkItem(bookmarkModel: BookmarkModel) {
            fun findIndex(item: BookmarkModel?): Int {
                val currentBookmarkList = bookmarkList.value.orEmpty().toMutableList()
                val findBookmark = currentBookmarkList.find {
                    it.id == item?.id
                }
                return currentBookmarkList.indexOf(findBookmark)
            }

            val findPosition = findIndex(bookmarkModel)
            if(findPosition < 0) {
                return
            }

            val currentBookmarkList = bookmarkList.value.orEmpty().toMutableList()
            currentBookmarkList.removeAt(findPosition)
            _bookmarkList.value = currentBookmarkList
        }
        if(bookmarkModel == null) {
            return
        }

        if (bookmarkModel.isSwitch) {
            addBookmarkItem(bookmarkModel)
        } else {
            removeBookmarkItem(bookmarkModel)
        }
    }
}