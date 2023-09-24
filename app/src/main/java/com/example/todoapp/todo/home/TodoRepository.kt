package com.example.todoapp.todo.home

interface TodoRepository {
    fun getGenerateId(): Long

    fun getTestData(): List<TodoModel>
}

class TodoRepositoryImpl(
    private val locale: TodoLocalDataSource
) : TodoRepository {

    override fun getGenerateId(): Long {
        return locale.getGenerateId()
    }

    override fun getTestData(): List<TodoModel> {
        return locale.getTestData()
    }
}