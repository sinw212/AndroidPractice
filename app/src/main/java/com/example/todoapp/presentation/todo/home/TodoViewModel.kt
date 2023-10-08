package com.example.todoapp.presentation.todo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.atomic.AtomicLong

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _list: MutableLiveData<List<TodoModel>> = MutableLiveData()
    val list: LiveData<List<TodoModel>> get() = _list //읽기만 가능한 변수

    init {
        _list.value = repository.getTestData()
    }

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
                    id = repository.getGenerateId()
                )
            )
        }
    }

    fun modifyTodoItem(
        todoModel: TodoModel?,
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

        val findPosition = findIndex(todoModel)
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

class TodoViewModelFactory : ViewModelProvider.Factory {
    private val todoRepository = TodoRepositoryImpl(TodoLocalDataSource())
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(todoRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}