package com.example.todoapp.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.todoapp.databinding.FragmentBookmarkBinding
import com.example.todoapp.todo.TodoFragment
import com.example.todoapp.todo.TodoModel

class BookmarkFragment : Fragment() {
//    companion object {
//        fun newInstance(id: String): BookmarkFragment {
//            val fragment = BookmarkFragment()
//            fragment.arguments = Bundle().apply {
//                bundleOf("id" to id)
//            }
//            return BookmarkFragment()
//        }
//    }

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkListAdapter by lazy {
        BookmarkListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
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

    override fun onDestroyView() {
        _binding = null // 구글 권장 - 메모리 누수 방지
        super.onDestroyView()
    }
}