package com.example.todoapp.todo.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.FragmentTodoBinding
import com.example.todoapp.main.MainActivity
import com.example.todoapp.todo.add.TodoContentActivity
import com.example.todoapp.todo.add.TodoContentType
import java.util.concurrent.atomic.AtomicLong

class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val idGenerate = AtomicLong(1L)
    private val viewModel: TodoViewModel by viewModels { TodoViewModel.TodoViewModelFactory(idGenerate) }

    private val editTodoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val todoContentType =
                    result.data?.getStringExtra(TodoContentActivity.EXTRA_TODO_CONTENT_TYPE)

                val position = result.data?.getIntExtra(TodoContentActivity.EXTRA_POSITION, -1)

                val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(
                        TodoContentActivity.EXTRA_MODEL,
                        TodoModel::class.java
                    )
                } else {
                    result.data?.getParcelableExtra(TodoContentActivity.EXTRA_MODEL)
                }

                when (TodoContentType.from(todoContentType)) {
                    TodoContentType.EDIT -> modifyTodoItem(todoModel, position)
                    TodoContentType.REMOVE -> removeTodoItem(position)
                    else -> Unit //nothing
                }
            }
        }

    private val todoListAdapter by lazy {
        TodoListAdapter(
            itemClickListener = { item, position ->
                editTodoLauncher.launch(
                    TodoContentActivity.newIntentForEdit(
                        requireContext(),
                        position,
                        item
                    )
                )
            },
            switchClickListener = { item, position ->
                updateBookmarkList(item)
                modifyTodoItem(item.copy(isSwitch = !item.isSwitch), position)
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
        initViewModel()
    }

    private fun initView() = with(binding) {
        todoList.adapter = todoListAdapter
    }

    private fun initViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
            todoListAdapter.submitList(it)
        }
    }

    fun addTodoItem(todoModel: TodoModel?) {
        viewModel.addTodoItem(todoModel)
    }

    fun modifyTodoItem(todoModel: TodoModel?, position: Int? = null) {
        viewModel.modifyTodoItem(todoModel, position)
    }

    private fun removeTodoItem(position: Int?) {
        viewModel.removeTodoItem(position)
    }

    private  fun updateBookmarkList(todoModel: TodoModel?) {
        if (todoModel == null) {
            return
        }
        if (!todoModel.isSwitch) {
            (activity as? MainActivity)?.getBookmarkFragment()?.addBookmarkContent(todoModel.toBookmarkModel())
        } else {
            (activity as? MainActivity)?.getBookmarkFragment()?.removeBookmarkContent(todoModel.toBookmarkModel())
        }
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}