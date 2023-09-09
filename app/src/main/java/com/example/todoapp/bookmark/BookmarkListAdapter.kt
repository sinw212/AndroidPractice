package com.example.todoapp.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemBookmarkBinding
import com.example.todoapp.todo.home.TodoListManager
import com.example.todoapp.todo.home.TodoModel

class BookmarkListAdapter(
    private val todoListManager: TodoListManager,
    val switchClickListener: (TodoModel, Int) -> Unit
) : RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {

    private var bookmarkList = ArrayList<TodoModel>()

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookmarkList[position])
    }

    inner class ViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoModel) = with(binding) {
            txtBookmarkTitle.text = item.title
            txtBookmarkContent.text = item.content
            switchBookmark.isChecked = item.isSwitch
            switchBookmark.setOnClickListener {
                switchClickListener(item, adapterPosition)
            }
        }
    }

    fun updateSwitch(todoModel: TodoModel?, position: Int?) {
        if(todoModel == null || position == null) {
            return
        }
        todoListManager.updateSwitch(todoModel, position)
        notifyItemChanged(position)
    }

    fun updateBookmarkList() {
        this.bookmarkList = todoListManager.bookmarkList
        notifyDataSetChanged()
    }
}