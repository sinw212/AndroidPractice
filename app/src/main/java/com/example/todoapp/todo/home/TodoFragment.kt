package com.example.todoapp.todo.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.todoapp.databinding.FragmentTodoBinding
import com.example.todoapp.main.MainSharedEventForTodo
import com.example.todoapp.main.MainSharedViewModel
import com.example.todoapp.todo.add.TodoContentActivity
import com.example.todoapp.todo.add.TodoContentType

class TodoFragment : Fragment() {

    companion object {
        fun newInstance() = TodoFragment()
    }

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by viewModels { TodoViewModelFactory() }
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

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
                    TodoContentType.EDIT -> modifyTodoItem(todoModel)
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
            switchClickListener = { item ->
                modifyTodoItem(item)
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

    private fun initViewModel() {
        with(todoViewModel) {
            list.observe(viewLifecycleOwner) {
                todoListAdapter.submitList(it)
                sharedViewModel.updateBookmarkItems(it)
            }
        }

        with(sharedViewModel) {
            todoEvent.observe(viewLifecycleOwner) { event ->
                when(event) {
                    is MainSharedEventForTodo.UpdateTodoItem -> {
                        todoViewModel.modifyTodoItem(event.item)
                    }
                    else -> Unit
                }
            }
        }
    }

    fun addTodoItem(todoModel: TodoModel?) {
        todoViewModel.addTodoItem(todoModel)
    }

    private fun modifyTodoItem(todoModel: TodoModel?) {
        todoViewModel.modifyTodoItem(todoModel)
    }

    private fun removeTodoItem(position: Int?) {
        todoViewModel.removeTodoItem(position)
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}