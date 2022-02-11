package com.example.mediamonksexercise.presentation.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediamonksexercise.domain.model.Album
import com.example.mediamonksexercise.domain.model.Resource
import com.example.mediamonksexercise.domain.model.Resource.Status.*
import com.example.mediamonksexercise.domain.repository.ContentRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val contentRepository: ContentRepository
): ViewModel() {

    private val _albums = MutableLiveData<List<Album>?>()
    val albums: LiveData<List<Album>?>
        get() = _albums

    private val _responseStatus = MutableLiveData<Resource.Status>()
    val responseStatus: LiveData<Resource.Status>
        get() = _responseStatus

    init {
        getListOfAlbums()
    }

    private fun getListOfAlbums() = viewModelScope.launch {
        contentRepository.getAlbums().collect {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { data -> _albums.postValue(data) }
                }
                ERROR -> {
                    _albums.postValue(ArrayList())
                }
                LOADING -> {
                    // todo
                }
            }
        }
    }
}