package com.example.admin.kolinmvvm3.ui

import androidx.lifecycle.ViewModel;
import com.example.admin.kolinmvvm3.data.foreCastRepository
import com.example.admin.kolinmvvm3.internal.UnitSystem

class CurrentWeatherViewModel(private val repository: foreCastRepository) : ViewModel() {
    private val unitSystem=UnitSystem.IMPERIAL
    val isMetric: Boolean
        get() = unitSystem==UnitSystem.METRIC
    val
}
