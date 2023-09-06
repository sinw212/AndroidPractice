package com.example.todoapp.todo.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class TodoListAdapter(val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {
    val todoList = ArrayList<TodoModel>()

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

    inner class ViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) = with(binding) {
            root.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    itemClickListener(position)
                }
            }
            txtTodoTitle.text = item.title
            txtTodoContent.text = item.content
        }
    }

    fun addItem(todoModel: TodoModel?) {
        if (todoModel == null) return
        todoList.add(todoModel)
        notifyItemChanged(todoList.size - 1)
    }

    fun updateItem(todoModel: TodoModel?, position: Int?) {
        if (todoModel == null || position == null) {
            return
        }
        todoList[position] = todoModel.copy(title = todoModel.title, content = todoModel.content)
        notifyItemChanged(position)
    }

    fun deleteItem(position: Int?) {
        if (position == null) return
        todoList.removeAt(position)
        notifyItemRemoved(position)
    }
}