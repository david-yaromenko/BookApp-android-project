package com.example.retrofitcompose.Data

import android.app.Application


//6.

class BookApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}