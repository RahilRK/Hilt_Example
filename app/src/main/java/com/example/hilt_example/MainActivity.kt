package com.example.hilt_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hilt_example.util.UserRepositoryInterface
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    @Named("RoomRepository")
    lateinit var repository: UserRepositoryInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository.saveUser("MainActivity","123456")
    }
}