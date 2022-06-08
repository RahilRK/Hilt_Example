package com.example.hilt_example.network

import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Interface used for api
 */
@Singleton
interface ApiInterface {

    @GET("products")
    suspend fun getProducts() : Response<ProductData>
}
