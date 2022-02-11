package com.example.mediamonksexercise.data

import com.example.mediamonksexercise.domain.model.Album
import com.example.mediamonksexercise.domain.model.Photo
import retrofit2.Response
import retrofit2.http.GET

interface ContentService {

    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("/photos")
    suspend fun getPhotos(): Response<List<Photo>>
}