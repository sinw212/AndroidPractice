package com.example.searchimage.presentation.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.searchimage.R
import com.example.searchimage.databinding.ItemBookmarkImageBinding
import com.example.searchimage.databinding.ItemBookmarkVideoBinding
import com.example.searchimage.databinding.ItemUnknownBinding
import java.text.SimpleDateFormat

class BookmarkListAdapter(
    private val bookmarkClickListener: (BookmarkItem, Int) -> Unit
) : ListAdapter<BookmarkItem, BookmarkListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<BookmarkItem>() {
        override fun areItemsTheSame(
            oldItem: BookmarkItem,
            newItem: BookmarkItem
        ): Boolean = if (oldItem is BookmarkItem.ImageItem && newItem is BookmarkItem.ImageItem) {
            oldItem.imgThumbnail == newItem.imgThumbnail
        } else if (oldItem is BookmarkItem.VideoItem && newItem is BookmarkItem.VideoItem) {
            oldItem.imgThumbnail == newItem.imgThumbnail
        } else {
            oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BookmarkItem,
            newItem: BookmarkItem
        ): Boolean = oldItem == newItem
    }
) {

    enum class BookmarkItemViewType {
        IMAGE, VIDEO
    }

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {

        abstract fun onBind(item: BookmarkItem)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is BookmarkItem.ImageItem -> BookmarkItemViewType.IMAGE.ordinal
        is BookmarkItem.VideoItem -> BookmarkItemViewType.VIDEO.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            BookmarkItemViewType.IMAGE.ordinal ->
                ImageViewHolder(
                    ItemBookmarkImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    bookmarkClickListener
                )

            BookmarkItemViewType.VIDEO.ordinal ->
                VideoViewHolder(
                    ItemBookmarkVideoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    bookmarkClickListener
                )

            else -> UnknownViewHolder(
                ItemUnknownBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ImageViewHolder(
        private val binding: ItemBookmarkImageBinding,
        private val bookmarkClickListener: (BookmarkItem, Int) -> Unit
    ) : ViewHolder(binding.root) {

        override fun onBind(item: BookmarkItem) = with(binding) {
            if (item is BookmarkItem.ImageItem) {
                imgThumbnail.load(item.imgThumbnail) {
                    placeholder(R.drawable.ic_img_placeholder)
                    error(R.drawable.ic_img_error)
                }
                imgBookmark.setImageResource(if (item.isBookmark) R.drawable.ic_bookmark else R.drawable.ic_bookmark_not)
                txtTitle.text = item.txtTitle
                txtDateTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.date)
                imgBookmark.setOnClickListener {
                    bookmarkClickListener(item.copy(isBookmark = !item.isBookmark), adapterPosition)
                }
            }
        }
    }

    class VideoViewHolder(
        private val binding: ItemBookmarkVideoBinding,
        private val bookmarkClickListener: (BookmarkItem, Int) -> Unit
    ) : ViewHolder(binding.root) {

        override fun onBind(item: BookmarkItem) = with(binding) {
            if (item is BookmarkItem.VideoItem) {
                imgThumbnail.load(item.imgThumbnail) {
                    placeholder(R.drawable.ic_img_placeholder)
                    error(R.drawable.ic_img_error)
                }
                imgBookmark.setImageResource(if (item.isBookmark) R.drawable.ic_bookmark else R.drawable.ic_bookmark_not)
                txtTitle.text = item.txtTitle
                txtDateTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.date)
                imgBookmark.setOnClickListener {
                    bookmarkClickListener(item.copy(isBookmark = !item.isBookmark), adapterPosition)
                }
            }
        }
    }

    class UnknownViewHolder(
        binding: ItemUnknownBinding
    ) : ViewHolder(binding.root) {

        override fun onBind(item: BookmarkItem) = Unit
    }
}