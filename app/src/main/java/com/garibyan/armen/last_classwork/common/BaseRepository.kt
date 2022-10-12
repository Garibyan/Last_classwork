package com.garibyan.armen.last_classwork.common

import retrofit2.Response
import javax.inject.Inject

class BaseRepository @Inject constructor(){

    suspend fun <T> saveApiCall(
        request: suspend() -> Response<List<T>>
    ): Resource<List<T>> {
        return try {
            val result = request.invoke()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                return Resource.Success(body)
            } else {
                Resource.Error(result.message() ?: "")
            }
        } catch (e: Throwable){
            Resource.Error(e.message.toString())
        }
    }

}