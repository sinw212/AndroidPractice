package com.example.todoapp.todo.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val todoListAdapter by lazy {
        TodoListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        todoList.adapter = todoListAdapter
    }

    fun setTodoContent(todoModel: TodoModel?) {
        todoListAdapter.addItem(todoModel)
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}