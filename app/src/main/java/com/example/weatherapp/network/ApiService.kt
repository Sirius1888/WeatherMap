package com.example.weatherapp.network

import com.example.weatherapp.model.WeatherMainModel
import com.example.weatherapp.model.city.CityDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val WEATHER_KEY = "c6e381d8c7ff98f0fee43775817cf6ad"
interface ApiService {

    @GET("data/2.5/weather")
    fun getWeatherData(@Query("units") units: String,
                       @Query("lat") lat: String,
                       @Query("lon") lon: String,
                       @Query("appId") appId: String) : Call<WeatherMainModel>

    @GET("rest/v2/capital/{city}")
    fun getCityData(@Path("city") capital: String) : Call<List<CityDataModel>>
}
