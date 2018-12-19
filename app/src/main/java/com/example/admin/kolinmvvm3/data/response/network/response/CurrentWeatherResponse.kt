package com.example.admin.kolinmvvm3.data.response.network.response

import com.example.admin.kolinmvvm3.data.response.db.entity.Current
import com.example.admin.kolinmvvm3.data.response.db.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
        @SerializedName("current")
        val current: Current,
        @SerializedName("location")
        val location: Location
)