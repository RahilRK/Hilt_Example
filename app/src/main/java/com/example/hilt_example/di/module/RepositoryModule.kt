package com.example.hilt_example.di.module

import com.example.hilt_example.network.ApiInterface
import com.example.hilt_example.recyclerView.ProductListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideProductRepository(apiInterface: ApiInterface): ProductListRepository {
        return ProductListRepository(apiInterface)
    }
}