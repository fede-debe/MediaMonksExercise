package com.example.mediamonksexercise.presentation.ui.photos.photos_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediamonksexercise.domain.model.Photo
import com.example.mediamonksexercise.domain.model.Resource.Status.*
import com.example.mediamonksexercise.domain.repository.ContentRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val contentRepository: ContentRepository
): ViewModel() {

    private val _photos = MutableLiveData<List<Photo>?>()
    val photos: LiveData<List<Photo>?> = _photos

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading

    private val _networkError = MutableLiveData<Boolean?>()
    val networkError: LiveData<Boolean?> = _networkError

    private val _navigateToPhotoDetails = MutableLiveData<Photo?>()
    val navigateToPhotoDetails: LiveData<Photo?> = _navigateToPhotoDetails

    fun getListOfPhotos() = viewModelScope.launch {
        contentRepository.getPhotos().collect {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { data -> _photos.postValue(data) }
                    _isLoading.postValue(false)
                    _networkError.postValue(false)
                }
                ERROR -> {
                    it.errorResponse?.message?.let { msg -> _errorMessage.postValue(msg) }
                    _photos.postValue(ArrayList())
                    _isLoading.postValue(false)
                    _networkError.postValue(true)
                }
                LOADING -> {
                    _isLoading.postValue(true)
                    _networkError.postValue(false)
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