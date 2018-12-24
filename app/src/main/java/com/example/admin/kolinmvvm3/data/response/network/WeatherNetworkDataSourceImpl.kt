package com.example.admin.kolinmvvm3.data.response.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.admin.kolinmvvm3.data.ApixvApiService
import com.example.admin.kolinmvvm3.data.response.network.response.CurrentWeatherResponse
import com.example.admin.kolinmvvm3.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(private val apixuWeatherService: ApixvApiService) : WeatherNetworkDataSource {
    private val _downLoadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downLoadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downLoadedCurrentWeather
    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try{
            val fetchedCurrentWeather = apixuWeatherService
                    .getCurrentWeather(location, languageCode)
                    .await()
            _downLoadedCurrentWeather.postValue(fetchedCurrentWeather)
        }catch (e:NoConnectivityException){
            Log.e("Connectivity","No Internet connection",e)
        }
    }
}