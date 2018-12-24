package com.example.admin.kolinmvvm3

import android.app.Application
import com.example.admin.kolinmvvm3.data.ApixvApiService
import com.example.admin.kolinmvvm3.data.db.CurrentWeatherDao
import com.example.admin.kolinmvvm3.data.db.ForecastDatabase
import com.example.admin.kolinmvvm3.data.foreCastRepository
import com.example.admin.kolinmvvm3.data.foreCastRepositoryImpl
import com.example.admin.kolinmvvm3.data.response.network.ConnectivityIntercept
import com.example.admin.kolinmvvm3.data.response.network.ConnectivityInterceptImpl
import com.example.admin.kolinmvvm3.data.response.network.WeatherNetworkDataSource
import com.example.admin.kolinmvvm3.data.response.network.WeatherNetworkDataSourceImpl
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityIntercept>() with singleton { ConnectivityInterceptImpl(instance()) }
        bind() from singleton { ApixvApiService(instance())}
        bind<WeatherNetworkDataSource>() with  singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<foreCastRepository>() with  singleton { foreCastRepositoryImpl(instance(),instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}