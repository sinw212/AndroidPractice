package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.TodoItem
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter(var todoItems: MutableList<TodoItem>) :
    RecyclerView.Adapter<TodoAdapter.TodoItemViewHolder>() {

    inner class TodoItemViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : TodoItem) {
            binding.todoTextView.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}