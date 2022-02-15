package com.example.mediamonksexercise.domain.repository

import com.example.mediamonksexercise.data.ContentRemoteDataSource
import com.example.mediamonksexercise.domain.model.Resource
import com.example.mediamonksexercise.domain.model.Resource.Status.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val remoteDataSource: ContentRemoteDataSource,
) {

    suspend fun getAlbums() = flow {
        emit(Resource.loading(null))
        val response = remoteDataSource.getAlbums()
        when (response.status) {
            SUCCESS -> {
                emit(Resource.success(response.data))
            }
            ERROR -> {
                emit(Resource.error(response.errorResponse, null))
            }
            LOADING -> {
                emit(Resource.loading(null))
            }
        }
    }

    suspend fun getPhotos() = flow {
        emit(Resource.loading(null))
        val response = remoteDataSource.getPhotos()
        when (response.status) {
            SUCCESS -> {
                emit(Resource.success(response.data))
            }
            ERROR -> {
                emit(Resource.error(response.errorResponse, null))
            }
            LOADING -> {
                emit(Resource.loading(null))
            }
        }
    }
}