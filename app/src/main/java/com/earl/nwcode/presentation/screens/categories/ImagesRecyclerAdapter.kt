package com.earl.nwcode.presentation.screens.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.nwcode.databinding.RecyclerImageItemBinding
import com.earl.nwcode.presentation.models.ImageUi

interface OnImageClickListener {
    fun onImageClick(image: String)
}

class ImagesRecyclerAdapter(
    private val clickListener: OnImageClickListener
) : ListAdapter<ImageUi, ImagesRecyclerAdapter.ItemViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RecyclerImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onImageClick(getItem(position).provideLargeImageLink())
        }
    }

    inner class ItemViewHolder(private val binding: RecyclerImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageUi) {
            item.recyclerDetails(binding.imageView, binding.progressBar)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<ImageUi>() {
        override fun areItemsTheSame(oldItem: ImageUi, newItem: ImageUi) = oldItem.same(newItem)
        override fun areContentsTheSame(oldItem: ImageUi, newItem: ImageUi) = oldItem.equals(newItem)
    }
}