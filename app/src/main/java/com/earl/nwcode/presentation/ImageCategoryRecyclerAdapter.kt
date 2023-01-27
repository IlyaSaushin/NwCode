package com.earl.nwcode.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.nwcode.databinding.RecyclerImageCategoryItemBinding

interface OnImageCategoryClickListener {
    fun onClick(item: String)
}

class ImageCategoryRecyclerAdapter(
    private val clickListener: OnImageCategoryClickListener
) : ListAdapter<String, ImageCategoryRecyclerAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RecyclerImageCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onClick(getItem(position))
        }
    }

    inner class ItemViewHolder(private val binding: RecyclerImageCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.categoryTitle.text = item
        }
    }

    companion object Diff : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem.equals(newItem)
    }
}