package com.example.admin.kolinmvvm3.data.response.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val temperture: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}