package com.example.admin.kolinmvvm3.data.response.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    companion object {
        @Volatile
        private var instance: ForecastDatabase? = null
        private val LOCK=Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: buildDataBase(context)
        }
        private fun buildDataBase(context: Context):ForecastDatabase{
                return Room.databaseBuilder(context.applicationContext,ForecastDatabase::class.java,"forecast.db").build()
        }
    }
}