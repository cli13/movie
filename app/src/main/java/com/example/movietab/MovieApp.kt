package com.example.movietab

import android.app.Application
import com.example.movietab.DI.ApiComponent

class MovieApp : Application() {
    private lateinit var _appComponent: ApiComponent
    val appComponent: ApiComponent
        get() = _appComponent

    override fun onCreate() {
        super.onCreate()
       // _appComponent = DaggerApiComponent.create()
    }
}