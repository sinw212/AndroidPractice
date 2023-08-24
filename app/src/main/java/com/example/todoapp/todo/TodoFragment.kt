package com.example.todoapp.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.todoapp.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
//    companion object {
//        fun newInstance(id: String): TodoFragment {
//            val fragment = TodoFragment()
//            fragment.arguments = Bundle().apply {
//                bundleOf("id" to id)
//            }
//            return TodoFragment()
//        }
//    }

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

    fun updateList(txtTitle: String, txtContent: String) {
        todoListAdapter.addItems(arrayListOf(TodoModel(todoListAdapter.itemCount, txtTitle, txtContent)))
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}