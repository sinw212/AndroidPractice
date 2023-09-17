package com.example.todoapp.bookmark

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookmarkModel(
    val id: Long,
    val title: String?,
    val content: String?,
    val isSwitch: Boolean = false
) : Parcelable
