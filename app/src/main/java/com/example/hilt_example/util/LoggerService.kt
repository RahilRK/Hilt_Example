package com.example.hilt_example.util

import android.util.Log
import javax.inject.Inject

class LoggerService @Inject constructor(){

    fun logIt(tag: String, message: String) {
        Log.d(tag, message)
    }
}