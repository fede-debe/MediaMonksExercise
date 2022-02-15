package com.example.mediamonksexercise.presentation.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediamonksexercise.domain.model.Album
import com.example.mediamonksexercise.domain.model.Resource.Status.*
import com.example.mediamonksexercise.domain.repository.ContentRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>?>()
    val albums: LiveData<List<Album>?> = _albums

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading

    private val _networkError = MutableLiveData<Boolean?>()
    val networkError: LiveData<Boolean?> = _networkError

    fun getListOfAlbums() = viewModelScope.launch {
        contentRepository.getAlbums().collect {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { data -> _albums.postValue(data) }
                    _isLoading.postValue(false)
                    _networkError.postValue(false)
                }
                ERROR -> {
                    it.errorResponse?.message?.let { msg -> _errorMessage.postValue(msg) }
                    _albums.postValue(ArrayList())
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
}