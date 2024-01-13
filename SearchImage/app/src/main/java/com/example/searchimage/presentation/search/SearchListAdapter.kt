package com.example.searchimage.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.searchimage.R
import com.example.searchimage.databinding.ItemSearchImageBinding
import com.example.searchimage.databinding.ItemSearchVideoBinding
import com.example.searchimage.databinding.ItemUnknownBinding
import java.text.SimpleDateFormat

class SearchListAdapter(
    private val bookmarkClickListener: (SearchItem) -> Unit
) : ListAdapter<SearchItem, SearchListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(
            oldItem: SearchItem,
            newItem: SearchItem
        ): Boolean = if (oldItem is SearchItem.ImageItem && newItem is SearchItem.ImageItem) {
            oldItem.imgThumbnail == newItem.imgThumbnail
        } else if (oldItem is SearchItem.VideoItem && newItem is SearchItem.VideoItem) {
            oldItem.imgThumbnail == newItem.imgThumbnail
        } else {
            oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchItem,
            newItem: SearchItem
        ): Boolean = oldItem == newItem
    }
) {

    enum class SearchItemViewType {
        IMAGE, VIDEO
    }

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {

        abstract fun onBind(item: SearchItem)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchItem.ImageItem -> SearchItemViewType.IMAGE.ordinal
        is SearchItem.VideoItem -> SearchItemViewType.VIDEO.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            SearchItemViewType.IMAGE.ordinal ->
                ImageViewHolder(
                    ItemSearchImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    bookmarkClickListener
                )

            SearchItemViewType.VIDEO.ordinal ->
                VideoViewHolder(
                    ItemSearchVideoBinding.inflate(
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
        private val binding: ItemSearchImageBinding,
        private val bookmarkClickListener: (SearchItem) -> Unit
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = with(binding) {
            if (item is SearchItem.ImageItem) {
                imgThumbnail.load(item.imgThumbnail) {
                    placeholder(R.drawable.ic_img_placeholder)
                    error(R.drawable.ic_img_error)
                }
                imgBookmark.setImageResource(if (item.isBookmark) R.drawable.ic_bookmark else R.drawable.ic_bookmark_not)
                txtTitle.text = item.txtTitle
                txtDateTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.date)
                imgBookmark.setOnClickListener {
                    bookmarkClickListener(item.copy(isBookmark = !item.isBookmark))
                }
            }
        }
    }

    class VideoViewHolder(
        private val binding: ItemSearchVideoBinding,
        private val bookmarkClickListener: (SearchItem) -> Unit
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = with(binding) {
            if (item is SearchItem.VideoItem) {
                imgThumbnail.load(item.imgThumbnail) {
                    placeholder(R.drawable.ic_img_placeholder)
                    error(R.drawable.ic_img_error)
                }
                imgBookmark.setImageResource(if (item.isBookmark) R.drawable.ic_bookmark else R.drawable.ic_bookmark_not)
                txtTitle.text = item.txtTitle
                txtDateTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.date)
                imgBookmark.setOnClickListener {
                    bookmarkClickListener(item.copy(isBookmark = !item.isBookmark))
                }
            }
        }
    }

    class UnknownViewHolder(
        binding: ItemUnknownBinding
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = Unit
    }
}