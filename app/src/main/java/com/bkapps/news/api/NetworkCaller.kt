package com.bkapps.news.api

import com.bkapps.news.utils.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class NetworkCaller {
    protected suspend fun <T : Any> getResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return NetworkResult.Success(body)
            }
            //Fired when not successful ma3neha not 2xx STATUS code
            return NetworkResult.Error(HttpException(response))
        } catch (e: IOException) {
            // IOException for network failures.
            return NetworkResult.Error(e)
        } catch (e: HttpException) {
            //Fired when not successful ma3neha not 2xx STATUS code
            return NetworkResult.Error(e)
        } catch (e: Exception) {
            //Fired when parsing error
            return NetworkResult.Error(java.lang.Exception("A error occurred ${e.message}"))
        }
    }
}