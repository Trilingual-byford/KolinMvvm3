package com.example.admin.kolinmvvm3.data.response.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.admin.kolinmvvm3.data.response.db.entity.Current
import com.example.admin.kolinmvvm3.data.response.db.entity.current_id
import com.example.admin.kolinmvvm3.data.response.db.unitlocalized.ImperialCurrentWeatherEntry
import com.example.admin.kolinmvvm3.data.response.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentWeather: Current)

    @Query("SELECT * FROM current_weather WHERE id=$current_id")
    fun getWeatherMetric(): MetricCurrentWeatherEntry

    @Query("SELECT * FROM current_weather WHERE id=$current_id")
    fun getWeatherImperial(): ImperialCurrentWeatherEntry
}