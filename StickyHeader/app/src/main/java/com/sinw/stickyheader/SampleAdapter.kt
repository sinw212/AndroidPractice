package com.sinw.stickyheader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sinw.stickyheader.databinding.ViewholderBottomBinding
import com.sinw.stickyheader.databinding.ViewholderHeaderBinding
import com.sinw.stickyheader.databinding.ViewholderSampleBinding
import com.sinw.stickyheader.databinding.ViewholderTopHolderBinding

class SampleAdapter : ListAdapter<ListItemData, SampleAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<ListItemData>() {
        override fun areItemsTheSame(
            oldItem: ListItemData,
            newItem: ListItemData
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ListItemData,
            newItem: ListItemData
        ): Boolean = oldItem == newItem
    }
) {

    enum class SampleItemViewType {
        HEADER, TOP_HOLDER, BOTTOM, ITEM
    }

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {
        abstract fun onBind(item: ListItemData)
    }

    fun isHeader(position: Int) = getItemViewType(position) == SampleItemViewType.TOP_HOLDER.ordinal

    fun getHeaderView(list: RecyclerView, position: Int): View? {
        val lastIndex = if (position < itemCount) position else itemCount - 1
        for (index in lastIndex downTo 0) {
            val item = getItem(index)
            if (item is ListItemData.TOP_HOLDER) {
                val binding =
                    ViewholderTopHolderBinding.inflate(
                        LayoutInflater.from(list.context),
                        list,
                        false
                    )
                binding.tvContents.text = item.item.toString()
                return binding.root
            }
        }
        return null
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ListItemData.HEADER -> SampleItemViewType.HEADER.ordinal
        is ListItemData.TOP_HOLDER -> SampleItemViewType.TOP_HOLDER.ordinal
        is ListItemData.BOTTOM -> SampleItemViewType.BOTTOM.ordinal
        is ListItemData.ITEM -> SampleItemViewType.ITEM.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            SampleItemViewType.HEADER.ordinal ->
                HeaderViewHolder(
                    ViewholderHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            SampleItemViewType.TOP_HOLDER.ordinal ->
                TopHolderViewHolder(
                    ViewholderTopHolderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            SampleItemViewType.BOTTOM.ordinal ->
                BottomViewHolder(
                    ViewholderBottomBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            SampleItemViewType.ITEM.ordinal ->
                SampleViewHolder(
                    ViewholderSampleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            else -> TODO("unknow viewtype : $viewType")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class HeaderViewHolder(
        private val binding: ViewholderHeaderBinding
    ) : ViewHolder(binding.root) {
        override fun onBind(item: ListItemData) = Unit
    }

    class TopHolderViewHolder(
        private val binding: ViewholderTopHolderBinding
    ) : ViewHolder(binding.root) {
        override fun onBind(item: ListItemData) = with(binding) {
            if (item is ListItemData.TOP_HOLDER) {
                tvContents.text = item.item.toString()
            }
        }
    }

    class BottomViewHolder(
        private val binding: ViewholderBottomBinding
    ) : ViewHolder(binding.root) {
        override fun onBind(item: ListItemData) = Unit
    }

    class SampleViewHolder(
        private val binding: ViewholderSampleBinding
    ) : ViewHolder(binding.root) {
        override fun onBind(item: ListItemData) = with(binding) {
            if (item is ListItemData.ITEM) {
                tvContents.text = item.item.toString()
            }
        }
    }
}

