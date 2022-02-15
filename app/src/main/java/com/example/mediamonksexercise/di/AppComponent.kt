package com.example.mediamonksexercise.di

import com.example.mediamonksexercise.presentation.ui.album.AlbumFragment
import com.example.mediamonksexercise.presentation.ui.photos.photos_list.PhotosFragment
import dagger.Component

@Component(
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent {
    fun injectAlbum(fragment: AlbumFragment)
    fun injectPhotos(fragment: PhotosFragment)
}