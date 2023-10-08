package com.example.todoapp.presentation.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.todoapp.databinding.FragmentBookmarkBinding
import com.example.todoapp.presentation.main.MainSharedEventForBookmark
import com.example.todoapp.presentation.main.MainSharedViewModel

class BookmarkFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkFragment()
    }

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter(
            switchClickListener = { item, position ->
                bookmarkViewModel.removeBookmarkItem(position)
                sharedViewModel.updateTodoItem(item)
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

    private fun initViewModel() {
        with(bookmarkViewModel) {
            list.observe(viewLifecycleOwner) {
                bookmarkListAdapter.submitList(it)
            }
        }

        with(sharedViewModel) {
            bookmarkEvent.observe(viewLifecycleOwner) { event ->
                when(event) {
                    is MainSharedEventForBookmark.UpdateBookmarkItems -> {
                        bookmarkViewModel.updateBookmarkItems(event.items)
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}