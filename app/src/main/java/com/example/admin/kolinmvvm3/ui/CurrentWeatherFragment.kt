package com.example.admin.kolinmvvm3.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.admin.kolinmvvm3.R
import com.example.admin.kolinmvvm3.data.ApixvApiService
import com.example.admin.kolinmvvm3.data.response.network.ConnectivityIntercept
import com.example.admin.kolinmvvm3.data.response.network.ConnectivityInterceptImpl
import com.example.admin.kolinmvvm3.data.response.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)

        val api = ApixvApiService.invoke(ConnectivityInterceptImpl(this.context!!))
        val weatherNetworkDataSourceImpl = WeatherNetworkDataSourceImpl(apixuWeatherService = api)
        weatherNetworkDataSourceImpl.downLoadedCurrentWeather.observe(this, Observer {
            current.setText(it.toString())
        } )

        GlobalScope.launch(Dispatchers.Main){
            weatherNetworkDataSourceImpl.fetchCurrentWeather("BeiJing","en")
        }
        // TODO: Use the ViewModel
    }

}
