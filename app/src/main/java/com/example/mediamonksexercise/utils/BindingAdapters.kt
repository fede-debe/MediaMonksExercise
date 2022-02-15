package com.example.mediamonksexercise.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mediamonksexercise.R
import com.example.mediamonksexercise.domain.model.Album
import com.example.mediamonksexercise.domain.model.Photo
import com.example.mediamonksexercise.domain.model.Resource
import com.example.mediamonksexercise.domain.model.Resource.Status.*
import com.example.mediamonksexercise.presentation.adapter.AlbumAdapter
import com.example.mediamonksexercise.presentation.adapter.PhotosAdapter

@BindingAdapter("bindImageUrl")
fun bindImageUrl(imageView: ImageView, imageUrl: String?) {
    imageView.load(imageUrl)
}

@BindingAdapter("responseStatus")
fun apiResponseStatus(imageView: ImageView, status: Resource.Status?) {
    when (status) {
        LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
        SUCCESS -> {
            imageView.visibility = View.GONE
        }
        ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_network_error)
        }
        else -> {
//            imageView.showSnackBar(R.string.app_name)
        }
    }
}

@BindingAdapter("listPhotos")
fun bindPhotosList(recyclerView: RecyclerView, photos: List<Photo>?) {
    val photoAdapter = recyclerView.adapter as PhotosAdapter
    photoAdapter.submitList(photos)
}

@BindingAdapter("listAlbums")
fun bindAlbumsList(recyclerView: RecyclerView, albums: List<Album>?) {
    val albumAdapter = recyclerView.adapter as AlbumAdapter
    albumAdapter.submitList(albums)
}