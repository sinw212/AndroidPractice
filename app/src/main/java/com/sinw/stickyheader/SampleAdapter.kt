package com.sinw.stickyheader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
class SampleAdapter : ListAdapter<ListItemData, RecyclerView.ViewHolder>(
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SampleItemViewType.HEADER.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_header, parent, false)
                HeaderViewHolder(view)
            }
            SampleItemViewType.TOP_HOLDER.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_top_holder, parent, false)
                TopHolderViewHolder(view)
            }
            SampleItemViewType.BOTTOM.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_bottom, parent, false)
                BottomViewHolder(view)
            }
            SampleItemViewType.ITEM.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_sample, parent, false)
                SampleViewHolder(view)
            }
            else -> TODO("unknow viewtype : $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindView()
            is TopHolderViewHolder -> holder.bindView()
            is BottomViewHolder -> holder.bindView()
            is SampleViewHolder -> holder.bindView(getItem(position))
        }
    }

    override fun getItemViewType(position: Int) = when(getItem(position)) {
        is ListItemData.HEADER -> SampleItemViewType.HEADER.ordinal
        is ListItemData.TOP_HOLDER -> SampleItemViewType.TOP_HOLDER.ordinal
        is ListItemData.BOTTOM -> SampleItemViewType.BOTTOM.ordinal
        is ListItemData.ITEM -> SampleItemViewType.ITEM.ordinal
    }

    fun isHeader(position: Int) = getItemViewType(position) == SampleItemViewType.TOP_HOLDER.ordinal
    fun getHeaderView(list: RecyclerView, position: Int): View? {
        val lastIndex =
            if (position < itemCount)
                position else itemCount - 1
        for (index in lastIndex downTo 0) {
            val model = getItemViewType(index)
            if (model == SampleItemViewType.TOP_HOLDER.ordinal) {
                return LayoutInflater.from(list.context)
                    .inflate(R.layout.viewholder_top_holder, list, false)
            }
        }

        return null
    }

    enum class SampleItemViewType {
        HEADER, TOP_HOLDER, BOTTOM, ITEM
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }

    inner class TopHolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }

    inner class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }

    inner class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContents = itemView.findViewById<TextView>(R.id.tvContents)

        fun bindView(data: ListItemData) {
            if(data is ListItemData.ITEM) {
                tvContents.text = data.item.toString()
            }
        }
    }
}

