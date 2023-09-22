package com.example.todoapp.todo.home

import android.os.Parcelable
import com.example.todoapp.bookmark.BookmarkModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoModel(
    val id: Long? = null,
    val title: String?,
    val content: String?,
    val isSwitch: Boolean = false
) : Parcelable

fun TodoModel.toBookmarkModel(): BookmarkModel {
    return BookmarkModel(
        id = id ?: 0,
        title = title,
        content = content,
        isSwitch = isSwitch
    )
}