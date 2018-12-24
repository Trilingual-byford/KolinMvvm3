package com.example.admin.kolinmvvm3.data.response.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.admin.kolinmvvm3.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptImpl(context: Context) : ConnectivityIntercept {
    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val systemServiceManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = systemServiceManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}