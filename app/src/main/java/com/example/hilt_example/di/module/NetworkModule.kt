package com.example.hilt_example.di.module

import com.example.hilt_example.BuildConfig
import com.example.hilt_example.network.ApiInterface
import com.example.hilt_example.network.HttpHandleIntercept
import com.example.hilt_example.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    /**
     * generate OKhttp client
     */
    @Provides
    fun getOkHttpClient(httpHandleIntercept: HttpHandleIntercept): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpHandleIntercept)
            .build()

        return builder.build()
    }

    @Provides
    fun provideHttpHandleIntercept(): HttpHandleIntercept = HttpHandleIntercept()

    //    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        val builder = Retrofit.Builder()
        builder.baseUrl(Constants.BASE_URL_FAKE_STORE)
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.client(okHttpClient)
        return builder.build()
    }

//    @Singleton
    @Provides
    @ViewModelScoped
    fun provideApiInterface(retrofit: Retrofit) : ApiInterface =
        retrofit.create(ApiInterface::class.java)
}