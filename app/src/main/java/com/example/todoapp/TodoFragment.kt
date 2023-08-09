package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.data.TodoItem
import com.example.todoapp.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
    var dataSet = mutableListOf<TodoItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodoBinding.inflate(inflater, container, false)
        binding.todoRecyclerView.apply {
            setHasFixedSize(true)
            adapter = TodoAdapter(dataSet)
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binding.floatingBtn.setOnClickListener {
            dataSet.add(TodoItem("todo ${dataSet.size + 1}"))
            binding.todoRecyclerView.scrollToPosition(binding.todoRecyclerView.adapter!!.itemCount - 1)
            binding.todoRecyclerView.adapter!!.notifyDataSetChanged()
        }

        return binding.root
    }
}