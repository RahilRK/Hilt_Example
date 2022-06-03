package com.example.hilt_example.util

import android.util.Log
import javax.inject.Inject

class RoomRepository @Inject constructor() : UserRepositoryInterface{

    val TAG = "RoomRepository"

    override fun saveUser(username: String, password: String) {
        Log.d(TAG, "saveUser: $username $password")
    }
}

class ApiRepository : UserRepositoryInterface{

    val TAG = "ApiRepository"

    override fun saveUser(username: String, password: String) {
        Log.d(TAG, "saveUser: $username $password")
    }
}

interface UserRepositoryInterface {
    fun saveUser(username: String, password: String)
}