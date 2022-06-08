
package com.example.hilt_example.recyclerView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hilt_example.network.ResponseHandler
import com.example.hilt_example.util.ViewModelBase
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productListRepository: ProductListRepository): ViewModelBase() {

    var responseLiveProductList = MutableLiveData<ResponseHandler<ProductData?>>()

    init {
        callApi()
    }

    private fun callApi() {
        viewModelScope.launch {
            responseLiveProductList.postValue(ResponseHandler.Loading)
            responseLiveProductList.value = productListRepository.getProductList()
        }
    }

}