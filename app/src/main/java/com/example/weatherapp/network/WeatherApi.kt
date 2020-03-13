package com.example.weatherapp.network

import com.example.weatherapp.model.city.CityModel
import com.example.weatherapp.model.weather.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val WEATHER_KEY = "c6e381d8c7ff98f0fee43775817cf6ad"

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("units") units: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appId") appId: String
    ): Call<WeatherModel>
}
