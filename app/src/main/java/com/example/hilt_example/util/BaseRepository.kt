package com.example.hilt_example.util

import android.util.Log
import com.example.hilt_example.network.ResponseHandler
import com.kotlinusermodule.network.model.HttpErrorCode
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import okhttp3.Response
import okhttp3.ResponseBody

open class BaseRepository {

    suspend fun <T : Any> makeAPICall(call: suspend () -> retrofit2.Response<T>): ResponseHandler<T?> {

        return try {

            var response: retrofit2.Response<T>? = null

            val res = flow { emit(call.invoke()) }
            res.collect {
                response =
                    retrofit2.Response.success(it.body()) //convert ResponseData<T> to Response<ResponseData<T>> & set response
            }

            return when {
                response!!.code() == 200 ->

                    ResponseHandler.OnSuccessResponse(response!!.body())

                else ->

                    ResponseHandler.OnFailed(
                    HttpErrorCode.UNAUTHORIZED.code,
                    response!!.message(),
                    "Error message code"
                )
            }

        } catch (e: Throwable) {
            ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code,
                Log.getStackTraceString(e),
                "Error message code")
        }
    }
}