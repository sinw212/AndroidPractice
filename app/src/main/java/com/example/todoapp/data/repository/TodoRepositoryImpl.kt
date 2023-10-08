package com.example.todoapp.data.repository

import com.example.todoapp.presentation.todo.home.TodoModel
import com.example.todoapp.presentation.todo.home.TodoRepository
import java.util.concurrent.atomic.AtomicLong

class TodoRepositoryImpl(
    private val idGenerate: AtomicLong
) : TodoRepository {

    private val list = mutableListOf<TodoModel>()

    override fun getTestData(): List<TodoModel> {
        list.addAll(
            arrayListOf<TodoModel>().apply {
                for (i in 0 until 3) {
                    add(
                        TodoModel(
                            idGenerate.getAndIncrement(),
                            "title $i",
                            "description $i"
                        )
                    )
                }
            }
        )
        return ArrayList<TodoModel>(list)
    }

    override fun addTodoItem(item: TodoModel?): List<TodoModel> {
        if (item == null) {
            return list
        }

        list.add(
            item.copy(
                id = idGenerate.getAndIncrement()
            )
        )
        return ArrayList<TodoModel>(list)
    }

    override fun modifyTodoItem(item: TodoModel?): List<TodoModel> {

        fun findIndex(item: TodoModel?): Int {
            val findTodo = list.find {
                it.id == item?.id
            }

            return list.indexOf(findTodo)
        }

        if (item == null) {
            return list
        }

        val findPosition = findIndex(item)
        if (findPosition < 0) {
            return list
        }

        list[findPosition] = item
        return ArrayList<TodoModel>(list)
    }

    override fun removeTodoItem(position: Int?): List<TodoModel> {
        if (position == null || position < 0) {
            return list
        }

        list.removeAt(position)
        return ArrayList<TodoModel>(list)
    }
}