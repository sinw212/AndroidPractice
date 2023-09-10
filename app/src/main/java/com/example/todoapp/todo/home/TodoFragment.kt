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

    private val editTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val todoContentType = result.data?.getStringExtra(TodoContentActivity.EXTRA_TODO_CONTENT_TYPE)

            val position = result.data?.getIntExtra(TodoContentActivity.EXTRA_POSITION, -1)

            val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL, TodoModel::class.java)
            } else {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL)
            }

            when(TodoContentType.from(todoContentType)) {
                TodoContentType.EDIT -> updateTodoContent(todoModel, position)
                TodoContentType.REMOVE -> removeTodoContent(position)
                else -> Unit //nothing
            }
        }
    }

    private val todoListAdapter by lazy {
        TodoListAdapter(
            itemClickListener = { item, position ->
                editTodoLauncher.launch(TodoContentActivity.newIntentForEdit(requireContext(), position, item))
            },
            switchClickListener = { item, position ->
                updateTodoSwitch(item, position)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
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

    fun addTodoContent(todoModel: TodoModel?) {
        todoListAdapter.addItem(todoModel)
    }

    private fun updateTodoContent(todoModel: TodoModel?, position: Int?) {
        todoListAdapter.updateItem(todoModel, position)
    }

    private fun removeTodoContent(position: Int?) {
        todoListAdapter.removeItem(position)
    }

    private fun updateTodoSwitch(todoModel: TodoModel?, position: Int?) {
        todoListAdapter.updateSwitch(todoModel, position)
    }

    override fun onResume() {
        super.onResume()
        todoListAdapter.updateTodoList()
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}