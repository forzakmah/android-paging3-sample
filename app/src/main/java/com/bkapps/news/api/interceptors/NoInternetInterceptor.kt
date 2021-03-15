package com.bkapps.news.api.interceptors

import com.bkapps.news.PagingApp
import com.bkapps.news.api.exceptions.NoNetworkException
import com.bkapps.news.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NoInternetInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable(PagingApp.getApplication())) {
            throw NoNetworkException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}