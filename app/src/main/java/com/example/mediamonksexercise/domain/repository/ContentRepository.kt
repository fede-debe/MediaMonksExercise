package com.example.mediamonksexercise.domain.repository

import com.example.mediamonksexercise.data.ContentRemoteDataSource
import com.example.mediamonksexercise.domain.model.Resource
import kotlinx.coroutines.flow.flow

class ContentRepository(
    private val remoteDataSource: ContentRemoteDataSource
) {

    suspend fun getAlbums() = flow {
        emit(Resource.loading(null))
        val response = remoteDataSource.getAlbums()
        when (response.status) {
            Resource.Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }
            Resource.Status.ERROR -> {
                emit(Resource.error(response.errorResponse, null))
            }
        }
    }

    suspend fun getPhotos() = flow {
        emit(Resource.loading(null))
        val response = remoteDataSource.getPhotos()
        when (response.status) {
            Resource.Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }
            Resource.Status.ERROR -> {
                emit(Resource.error(response.errorResponse, null))
            }
        }
    }
}