package com.example.hilt_example.recyclerView

import com.example.hilt_example.network.ApiInterface
import com.example.hilt_example.network.ResponseHandler
import com.example.hilt_example.util.BaseRepository
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductListRepository @Inject constructor(private val apiInterface: ApiInterface):
    BaseRepository() {

    suspend fun getProductList(): ResponseHandler<ProductData?> {
        return makeAPICall {
            apiInterface.getProducts()
        }
    }
}