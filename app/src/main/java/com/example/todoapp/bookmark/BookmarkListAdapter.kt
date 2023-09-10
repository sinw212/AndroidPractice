package com.example.todoapp.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemBookmarkBinding
import com.example.todoapp.todo.home.TodoListManager
import com.example.todoapp.todo.home.TodoModel

class BookmarkListAdapter(val switchClickListener: (TodoModel) -> Unit) : ListAdapter<TodoModel, BookmarkListAdapter.ViewHolder>(diffUtil) {

    override fun getItemCount(): Int {
        return TodoListManager.bookmarkList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(TodoListManager.bookmarkList[position])
    }

    inner class ViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) = with(binding) {
            txtBookmarkTitle.text = item.title
            txtBookmarkContent.text = item.content
            switchBookmark.isChecked = item.isSwitch
            switchBookmark.setOnClickListener {
                switchClickListener(item)
            }
        }
    }

    fun updateSwitch(todoModel: TodoModel?) {
        if(todoModel == null) {
            return
        }
        val updatePosition = TodoListManager.todoList.indexOfFirst { it.id == todoModel.id }
        TodoListManager.updateSwitch(todoModel, updatePosition)
        updateBookmarkList()
    }

    fun updateBookmarkList() {
        TodoListManager.updateBookmarkList()
        notifyDataSetChanged()
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<TodoModel>() {
            override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}