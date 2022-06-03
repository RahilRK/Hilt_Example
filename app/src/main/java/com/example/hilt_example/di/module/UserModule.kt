package com.example.hilt_example.di.module

import com.example.hilt_example.util.ApiRepository
import com.example.hilt_example.util.RoomRepository
import com.example.hilt_example.util.UserRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@InstallIn(ActivityComponent::class)
@Module
class UserModule {

    @Provides
    @Named("RoomRepository")
    fun providesRoomRepository(roomRepository: RoomRepository): UserRepositoryInterface = roomRepository

    @Provides
    @Named("ApiRepository")
    fun providesApiRepository(): UserRepositoryInterface = ApiRepository()
}