package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        binding.bookmarkRecyclerView.apply {
            setHasFixedSize(true)
            adapter = TodoAdapter(arrayListOf())
            layoutManager = LinearLayoutManager(requireActivity())
        }
        return binding.root
    }
}