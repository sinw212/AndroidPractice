package com.example.todoapp.todo.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoModel(
    val title: String,
    val content: String
) : Parcelable
