package com.example.admin.kolinmvvm3.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.admin.kolinmvvm3.data.db.entity.Current
import com.example.admin.kolinmvvm3.data.db.entity.current_id
import com.example.admin.kolinmvvm3.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.example.admin.kolinmvvm3.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentWeather: Current?)

    @Query("SELECT * FROM current_weather WHERE id=$current_id")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("SELECT * FROM current_weather WHERE id=$current_id")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}