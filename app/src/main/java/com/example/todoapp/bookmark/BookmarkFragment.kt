package com.example.todoapp.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.databinding.FragmentBookmarkBinding
import com.example.todoapp.todo.home.TodoModel

class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter(
            switchClickListener = { item ->
                updateTodoSwitch(item)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        bookmarkList.adapter = bookmarkListAdapter
    }

    private fun updateTodoSwitch(todoModel: TodoModel?) {
        bookmarkListAdapter.updateSwitch(todoModel)
    }

    override fun onResume() {
        super.onResume()
        bookmarkListAdapter.updateBookmarkList()
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}