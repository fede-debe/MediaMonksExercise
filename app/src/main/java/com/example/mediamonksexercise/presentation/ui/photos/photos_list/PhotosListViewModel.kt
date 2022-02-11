package com.example.mediamonksexercise.presentation.ui.photos.photos_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediamonksexercise.domain.model.Photo
import com.example.mediamonksexercise.domain.model.Resource
import com.example.mediamonksexercise.domain.model.Resource.Status.*
import com.example.mediamonksexercise.domain.repository.ContentRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotosViewModel(
    private val contentRepository: ContentRepository
): ViewModel() {

    private val _photos = MutableLiveData<List<Photo>?>()
    val photos: LiveData<List<Photo>?>
        get() = _photos

    private val _responseStatus = MutableLiveData<Resource.Status>()
    val responseStatus: LiveData<Resource.Status>
        get() = _responseStatus

    private val _navigateToPhotoDetails = MutableLiveData<Photo?>()
    val navigateToPhotoDetails: LiveData<Photo?>
        get() = _navigateToPhotoDetails

    init {
        getListOfPhotos()
    }

    private fun getListOfPhotos() = viewModelScope.launch {
        contentRepository.getPhotos().collect {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { data -> _photos.postValue(data) }
                }
                ERROR -> {
                    _photos.postValue(ArrayList())
                }
                LOADING -> {
                    // todo
                }
            }
        }
    }

    fun openPhotoDetails(photo: Photo) {
        _navigateToPhotoDetails.postValue(photo)
    }

    fun openPhotoDetailsComplete() {
        _navigateToPhotoDetails.postValue(null)
    }
}