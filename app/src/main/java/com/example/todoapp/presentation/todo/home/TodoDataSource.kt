package com.example.todoapp.presentation.todo.home

import java.util.concurrent.atomic.AtomicLong

interface TodoDataSource {
    fun getGenerateId(): Long

    fun getTestData(): List<TodoModel>
}

class TodoLocalDataSource: TodoDataSource {
    private val idGenerate: AtomicLong = AtomicLong(1L)
    private var list = ArrayList<TodoModel>()

    override fun getGenerateId(): Long {
        return idGenerate.getAndIncrement()
    }

    override fun getTestData(): List<TodoModel> {
        list = arrayListOf<TodoModel>().apply {
            for(i in 0 until 3) {
                add(
                    TodoModel(
                        getGenerateId(),
                        "title $i",
                        "description $i"
                    )
                )
            }
        }
        return list
    }
}