package com.example.admin.kolinmvvm3.data

import androidx.lifecycle.LiveData
import com.example.admin.kolinmvvm3.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface foreCastRepository{
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}