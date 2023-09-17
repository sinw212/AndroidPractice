package com.example.todoapp.todo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicLong

class TodoViewModel : ViewModel() {

    private val _list: MutableLiveData<List<TodoModel>> = MutableLiveData()
    val list: LiveData<List<TodoModel>> get() = _list //읽기만 가능한 변수

    private val idGenerate = AtomicLong(1L)

    fun addTodoItem(
        todoModel: TodoModel?
    ) {
        if (todoModel == null) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        _list.value = currentList.apply {
            add(
                todoModel.copy(
                    id = idGenerate.getAndIncrement()
                )
            )
        }
    }

    fun modifyTodoItem(
        todoModel: TodoModel?,
        position: Int?
    ) {

        fun findIndex(item: TodoModel?): Int {
            val currentList = list.value.orEmpty().toMutableList()
            val findTodo = currentList.find {
                it.id == item?.id
            }
            return currentList.indexOf(findTodo)
        }

        if (todoModel == null) {
            return
        }

        val findPosition = position ?: findIndex(todoModel)
        if (findPosition < 0) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList[findPosition] = todoModel
        _list.value = currentList
    }

    fun removeTodoItem(position: Int?) {
        if (position == null || position < 0) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(position)
        _list.value = currentList
    }
}