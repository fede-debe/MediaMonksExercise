package com.example.mediamonksexercise.presentation.ui.photos.photo_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mediamonksexercise.domain.model.Photo

class PhotoDetailsViewModelFactory(
    private val photo: Photo,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoDetailsViewModel::class.java)) {
            return PhotoDetailsViewModel(photo, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}