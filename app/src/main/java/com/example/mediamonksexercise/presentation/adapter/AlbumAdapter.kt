package com.example.mediamonksexercise.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mediamonksexercise.databinding.ItemAlbumViewBinding
import com.example.mediamonksexercise.domain.model.Album

class AlbumAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(DiffCallback) {

    class AlbumViewHolder(private var binding: ItemAlbumViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.item = album
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(ItemAlbumViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(album)
        }
        holder.bind(album)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (album: Album) -> Unit) {
        fun onClick(album: Album) = clickListener(album)
    }
}