package com.example.todoapp.todo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class TodoListAdapter(
    val itemClickListener: (TodoModel, Int) -> Unit,
    val switchClickListener: (TodoModel, Int) -> Unit,
) : ListAdapter<TodoModel, TodoListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<TodoModel>() {
        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) = with(binding) {
            txtTodoTitle.text = item.title
            txtTodoContent.text = item.content
            switchTodo.isChecked = item.isSwitch
            root.setOnClickListener {
                itemClickListener(item, adapterPosition)
            }
            switchTodo.setOnClickListener {
                switchClickListener(item, adapterPosition)
            }
        }
    }
}