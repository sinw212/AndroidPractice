package com.example.todoapp.todo.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.todoapp.databinding.FragmentTodoBinding
import com.example.todoapp.todo.add.TodoContentActivity
import com.example.todoapp.todo.add.TodoContentType

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val todoListAdapter by lazy {
        TodoListAdapter()
    }

    private val editTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val todoContentType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(TodoContentType.EXTRA_TODO_CONTENT_TYPE, TodoContentType::class.java)
            } else {
                result.data?.getParcelableExtra(TodoContentType.EXTRA_TODO_CONTENT_TYPE)
            }

            val position = result.data?.getIntExtra(TodoContentActivity.EXTRA_POSITION, -1)

            val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL, TodoModel::class.java)
            } else {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL)
            }

            if(todoContentType == TodoContentType.EDIT) {
                updateTodoContent(todoModel, position)
            } else {
                deleteTodoContent(position)
            }
        }
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

        todoListAdapter.itemClick = object: TodoListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                if (activity == null) return
                editTodoLauncher.launch(TodoContentActivity.newIntentForEdit(requireContext(), position, todoListAdapter.todoList[position]))
            }
        }
    }

    fun addTodoContent(todoModel: TodoModel?) {
        todoListAdapter.addItem(todoModel)
    }

    private fun updateTodoContent(todoModel: TodoModel?, position: Int?) {
        todoListAdapter.updateItem(todoModel, position)
    }

    private fun deleteTodoContent(position: Int?) {
        todoListAdapter.deleteItem(position)
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}