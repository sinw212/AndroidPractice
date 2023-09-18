package com.example.todoapp.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MediatorLiveData
import com.example.todoapp.databinding.FragmentBookmarkBinding
import com.example.todoapp.main.MainViewModel

class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val bookmarkListMediator = MediatorLiveData<List<BookmarkModel>>()

    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter(
            switchClickListener = { item ->
                removeBookmarkItem(item)
                modifyTodoItem(item)
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
        bookmarkViewModel.list.observe(viewLifecycleOwner) {
            mainViewModel.modifyBookmarkList(it)
        }
        mainViewModel.bookmarkList.observe(viewLifecycleOwner) {
            bookmarkListMediator.value = it
        }
        bookmarkListMediator.observe(viewLifecycleOwner) {
            bookmarkListAdapter.submitList(it)
        }
    }

    private fun removeBookmarkItem(bookmarkModel: BookmarkModel?) {
        bookmarkViewModel.removeBookmarkItem(bookmarkModel)
        mainViewModel.modifyBookmarkItem(bookmarkModel?.copy(isSwitch = false))
    }

    private fun modifyTodoItem(bookmarkModel: BookmarkModel?) {
        mainViewModel.modifyTodoItem(bookmarkModel?.toTodoModel())
    }

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}