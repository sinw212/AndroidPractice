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
) : ListAdapter<SearchItem, SearchListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(
            oldItem: SearchItem,
            newItem: SearchItem
        ): Boolean = if (oldItem is SearchItem.ImageItem && newItem is SearchItem.ImageItem) {
            oldItem.txtTitle == newItem.txtTitle
        } else if (oldItem is SearchItem.VideoItem && newItem is SearchItem.VideoItem) {
            oldItem.txtTitle == newItem.txtTitle
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
                    )
                )

            SearchItemViewType.VIDEO.ordinal ->
                VideoViewHolder(
                    ItemSearchVideoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
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
        private val binding: ItemSearchImageBinding
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = with(binding) {
            if (item is SearchItem.ImageItem) {
                imgThumbnail.load(item.imgThumbnail) {
                    placeholder(R.drawable.ic_no_image)
                    error(R.drawable.ic_no_image)
                }
                txtTitle.text = item.txtTitle
                txtDateTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.date)
            }
        }
    }

    class VideoViewHolder(
        private val binding: ItemSearchVideoBinding
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = with(binding) {
            if (item is SearchItem.VideoItem) {
                imgThumbnail.load(item.imgThumbnail) {
                    placeholder(R.drawable.ic_no_image)
                    error(R.drawable.ic_no_image)
                }
                txtTitle.text = item.txtTitle
                txtDateTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.date)
            }
        }
    }

    class UnknownViewHolder(
        binding: ItemUnknownBinding
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = Unit
    }
}