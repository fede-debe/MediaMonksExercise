package com.example.mediamonksexercise.presentation.ui.photos.photo_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mediamonksexercise.domain.model.Photo

class PhotoDetailsViewModel(photo: Photo, app: Application) : AndroidViewModel(app) {
    private val _selectedPhoto = MutableLiveData<Photo>()

    // The external LiveData for the SelectedProperty
    val selectedPhoto: LiveData<Photo>
        get() = _selectedPhoto

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedPhoto.value = photo
    }
}