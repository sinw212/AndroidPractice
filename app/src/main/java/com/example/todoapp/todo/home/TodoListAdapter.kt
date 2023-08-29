package com.example.todoapp.todo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class TodoListAdapter: RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val todoList = ArrayList<TodoModel>()

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    inner class ViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : TodoModel) = with(binding) {
            txtTodoTitle.text = item.title
            txtTodoContent.text = item.content
        }
    }

    fun addItem(todoModel: TodoModel?) {
        if(todoModel == null) {
            return
        }
        todoList.add(todoModel)
        notifyItemChanged(todoList.size - 1)
    }
}