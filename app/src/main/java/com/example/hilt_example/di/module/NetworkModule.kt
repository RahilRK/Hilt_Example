package com.example.hilt_example.di.module

import com.example.hilt_example.network.ApiInterface
import com.example.hilt_example.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    @RetrofitStore
    fun provideRetrofit(): Retrofit {

        val builder = Retrofit.Builder()
        builder.baseUrl(Constants.BASE_URL_FAKE_STORE)
        builder.addConverterFactory(GsonConverterFactory.create())
//            builder.client(getOkHttpClient())
        return builder.build()
    }

    @Provides
    @ViewModelScoped
    fun provideApiInterface(@RetrofitStore retrofit: Retrofit) : ApiInterface =
        retrofit.create(ApiInterface::class.java)

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitStore