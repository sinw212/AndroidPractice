package com.example.searchimage.presentation.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.searchimage.databinding.FragmentBookmarkBinding
import com.example.searchimage.presentation.main.MainSharedEventForBookmark
import com.example.searchimage.presentation.main.MainSharedViewModel

class BookmarkFragment: Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainSharedViewModel by activityViewModels()
    private val viewModel: BookmarkViewModel by viewModels {
        BookmarkViewModelFactory()
    }

    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter(
            bookmarkClickListener = { item, position ->
                viewModel.deleteBookmarkItem(position)
                sharedViewModel.updateSearchItem(item)
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
        recyclerViewBookmark.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = bookmarkListAdapter
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            bookmarkList.observe(viewLifecycleOwner) {
                bookmarkListAdapter.submitList(it)
            }
        }

        with(sharedViewModel) {
            bookmarkEvent.observe(viewLifecycleOwner) { event ->
                when(event) {
                    is MainSharedEventForBookmark.AddBookmarkItem -> {
                        viewModel.addBookmarkItem(event.item)
                    }
                    is MainSharedEventForBookmark.RemoveBookmarkItem -> {
                        viewModel.deleteBookmarkItem(event.item)
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}