package com.example.hilt_example.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application: Application() {

//    @Inject
//    lateinit var repository: UserRepository

    override fun onCreate() {
        super.onCreate()

//        repository.saveUser("Application","123456")
    }
}