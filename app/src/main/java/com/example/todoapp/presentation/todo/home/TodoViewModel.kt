package com.example.todoapp.presentation.todo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.repository.TodoRepositoryImpl
import java.util.concurrent.atomic.AtomicLong

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _list: MutableLiveData<List<TodoModel>> = MutableLiveData()
    val list: LiveData<List<TodoModel>> get() = _list //읽기만 가능한 변수

    init {
        _list.value = repository.getTestData()
    }

    fun addTodoItem(item: TodoModel?) {
        _list.value = repository.addTodoItem(item)
    }

    fun modifyTodoItem(item: TodoModel?, ) {
        _list.value = repository.modifyTodoItem(item)
    }

    fun removeTodoItem(position: Int?) {
        _list.value = repository.removeTodoItem(position)
    }
}

class TodoViewModelFactory : ViewModelProvider.Factory {
    private val todoRepository = TodoRepositoryImpl(AtomicLong(1L))
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(todoRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}