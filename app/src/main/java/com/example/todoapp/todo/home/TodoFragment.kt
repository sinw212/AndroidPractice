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

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val todoListAdapter by lazy {
        TodoListAdapter()
    }

    private var updatePosition = 0
    private val editTodoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL, TodoModel::class.java)
            } else {
                result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL)
            }
            updateTodoContent(todoModel)
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
                updatePosition = position
                editTodoLauncher.launch(TodoContentActivity.newIntentForEdit(activity, todoListAdapter.todoList[position]))
            }
        }
    }

    fun addTodoContent(todoModel: TodoModel?) {
        todoListAdapter.addItem(todoModel)
    }

    private fun updateTodoContent(todoModel: TodoModel?) {
        todoListAdapter.updateItem(todoModel, updatePosition)
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}