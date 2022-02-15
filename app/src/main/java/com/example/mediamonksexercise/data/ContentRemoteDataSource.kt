package com.example.mediamonksexercise.data

import javax.inject.Inject

class ContentRemoteDataSource @Inject constructor(
    private val contentService: ContentService
) : BaseRemoteDataSource() {

    suspend fun getAlbums() = getResult { contentService.getAlbums() }

    suspend fun getPhotos() = getResult { contentService.getPhotos() }
}