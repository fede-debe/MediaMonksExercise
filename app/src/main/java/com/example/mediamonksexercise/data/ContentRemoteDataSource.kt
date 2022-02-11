package com.example.mediamonksexercise.data

class ContentRemoteDataSource (
    private val contentService: ContentService
): BaseRemoteDataSource() {

    suspend fun getAlbums() = getResult { contentService.getAlbums() }

    suspend fun getPhotos() = getResult { contentService.getPhotos() }
}