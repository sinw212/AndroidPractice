package com.example.todoapp.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todoapp.databinding.FragmentBookmarkBinding
import com.example.todoapp.main.MainActivity
import com.example.todoapp.todo.home.TodoModel

class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookmarkViewModel by viewModels()

    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter(
            switchClickListener = { item ->
                removeBookmarkContent(item)
                updateTodoItem(item)
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
        initViewModel()
    }

    private fun initView() = with(binding) {
        bookmarkList.adapter = bookmarkListAdapter
    }

    private fun initViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
            bookmarkListAdapter.submitList(it)
        }
    }

    fun addBookmarkContent(bookmarkModel: BookmarkModel?) {
        viewModel.addBookmarkItem(bookmarkModel)
    }

    fun removeBookmarkContent(bookmarkModel: BookmarkModel?) {
        viewModel.removeBookmarkItem(bookmarkModel)
    }

    private fun updateTodoItem(bookmarkModel: BookmarkModel) {
        (activity as? MainActivity)?.getTodoFragment()?.modifyTodoItem(bookmarkModel.toTodoModel(), null)
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}