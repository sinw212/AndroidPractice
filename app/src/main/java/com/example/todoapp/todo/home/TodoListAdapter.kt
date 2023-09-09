package com.example.todoapp.todo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class TodoListAdapter(
    private val todoListManager: TodoListManager,
    val itemClickListener: (TodoModel, Int) -> Unit,
    val switchClickListener: (TodoModel, Int) -> Unit
) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return todoListManager.todoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoListManager.todoList[position])
    }

    inner class ViewHolder(
        private val binding: ItemTodoBinding,
        private val itemClickListener: (TodoModel, Int) -> Unit
    ) :
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
            todoListManager.addItem(todoModel)
            notifyItemChanged(todoListManager.todoList.size - 1)
        }
    }

    fun updateItem(todoModel: TodoModel?, position: Int?) {
        if(todoModel == null || position == null) {
            return
        }
        todoListManager.updateItem(todoModel, position)
        notifyItemChanged(position)
    }

    fun removeItem(position: Int?) {
        if (position == null) return
        todoListManager.removeItem(position)
        notifyItemRemoved(position)
    }

    fun updateSwitch(todoModel: TodoModel?, position: Int?) {
        if(todoModel == null || position == null) {
            return
        }
        todoListManager.updateSwitch(todoModel, position)
        notifyItemChanged(position)
    }
}