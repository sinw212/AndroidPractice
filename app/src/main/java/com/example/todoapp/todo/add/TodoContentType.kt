package com.example.todoapp.todo.add

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class TodoContentType: Parcelable {
    ADD, EDIT, DELETE;

    companion object {
        const val EXTRA_TODO_CONTENT_TYPE = "extra_todo_content_type"
    }
}