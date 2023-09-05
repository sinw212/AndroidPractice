package com.example.todoapp.todo.add

enum class TodoContentType {
    ADD, EDIT, DELETE;

    companion object {
        const val EXTRA_TODO_CONTENT_TYPE = "extra_todo_content_type"
    }
}