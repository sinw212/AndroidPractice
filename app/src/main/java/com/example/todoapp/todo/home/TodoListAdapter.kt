package com.example.todoapp.todo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class TodoListAdapter(
    val itemClickListener: (TodoModel, Int) -> Unit,
    val switchClickListener: (TodoModel, Int) -> Unit
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return TodoListManager.todoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(TodoListManager.todoList[position])
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

    fun addItem(todoModel: TodoModel?) {
        todoModel?.let {
            TodoListManager.addItem(todoModel)
            notifyItemChanged(TodoListManager.todoList.size - 1)
        }
    }

    fun updateItem(todoModel: TodoModel?, position: Int?) {
        if(todoModel == null || position == null) {
            return
        }
        TodoListManager.updateItem(todoModel, position)
        notifyItemChanged(position)
    }

    fun removeItem(position: Int?) {
        if (position == null) return
        TodoListManager.removeItem(position)
        notifyItemRemoved(position)
    }

    fun updateSwitch(todoModel: TodoModel?, position: Int?) {
        if(todoModel == null || position == null) {
            return
        }
        TodoListManager.updateSwitch(todoModel, position)
    }

    fun updateTodoList() {
        notifyDataSetChanged()
    }
}