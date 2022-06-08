package com.example.hilt_example.recyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.hilt_example.R
import com.example.hilt_example.databinding.ActivityProductListBinding
import com.example.hilt_example.network.ResponseHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductList : AppCompatActivity() {

    val TAG = "ProductListActivity"

    lateinit var binding: ActivityProductListBinding
    @Inject
    lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_list)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        setObserver()
    }

    private fun setObserver() {

        viewModel.responseLiveProductList.observe(this) { state ->

            when (state) {
                is ResponseHandler.Loading -> {
                    Log.d(TAG, "Loading...: ")
                    Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show()
                }
                is ResponseHandler.OnSuccessResponse -> {
                    state.response?.let {
                        Log.d(TAG, "ProductList number: ${state.response.size}")
                        Log.d(TAG, "ProductList: ${state.response}")
                        binding.model = state.response
                    }
                }
                is ResponseHandler.OnFailed -> {
                    Log.d(TAG, "OnFailed...: $state")
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}