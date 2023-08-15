package com.example.todoapp.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemBookmarkBinding

class BookmarkListAdapter: RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {

    private val bookmarkList = ArrayList<BookmarkModel>()

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

    inner class ViewHolder(private val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BookmarkModel) = with(binding) {
            bookmarkTextView.text = item.content
        }
    }

    fun addItems(items: List<BookmarkModel>) {
        bookmarkList.addAll(items)
        notifyDataSetChanged()
    }
}