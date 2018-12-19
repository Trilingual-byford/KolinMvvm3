package com.example.admin.kolinmvvm3.data

import com.example.admin.kolinmvvm3.data.response.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3cded79d4c354e5694a161956180812"
const val BASE_URL = "http://api.apixu.com/v1/"

interface ApixvApiService {
    //http://api.apixu.com/v1/current.json?key=3cded79d4c354e5694a161956180812&q=London&lang=en
    @GET("current.json")
    fun getCurrentWeather(
            @Query("q") city: String,
            @Query("lang") lang: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(): ApixvApiService {
            val Interceptor = Interceptor { chain ->
                val url = chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("key", API_KEY)
                        .build()
                val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                return@Interceptor chain.proceed(request)
            }
            val httpClient = OkHttpClient.Builder()
                    .addInterceptor(Interceptor)
                    .build()
            return Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApixvApiService::class.java)
        }
    }
}
