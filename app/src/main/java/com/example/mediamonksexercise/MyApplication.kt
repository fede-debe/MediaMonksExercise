package com.example.mediamonksexercise

import android.app.Application
import com.example.mediamonksexercise.di.AppComponent
import com.example.mediamonksexercise.di.DaggerAppComponent

class MyApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}