package com.example.admin.kolinmvvm3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.admin.kolinmvvm3.data.db.CurrentWeatherDao
import com.example.admin.kolinmvvm3.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.admin.kolinmvvm3.data.response.network.WeatherNetworkDataSource
import com.example.admin.kolinmvvm3.data.response.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class foreCastRepositoryImpl(
        private val currentWeatherDao: CurrentWeatherDao,
        private val weatherNetworkDataSource: WeatherNetworkDataSource
) : foreCastRepository {
    init {
        weatherNetworkDataSource.downLoadedCurrentWeather.observeForever { currentWeather ->
            persistFetchedCurrentWeather(currentWeather)
        }
    }

    private fun persistFetchedCurrentWeather(newCurrentWeather: CurrentWeatherResponse?) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.insert(newCurrentWeather?.current)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private suspend fun initWeatherData() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather("Los Angeles", Locale.getDefault().language)
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime):Boolean{
        val tmpTimeNow = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(tmpTimeNow)
    }
}