package com.example.admin.kolinmvvm3.data.response.network

import androidx.lifecycle.LiveData
import com.example.admin.kolinmvvm3.data.response.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downLoadedCurrentWeather: LiveData<CurrentWeatherResponse>
    suspend fun fetchCurrentWeather(
            location: String,
            languageCode: String
    )

}