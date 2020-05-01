package com.example.weatherapp.ui.map

import androidx.lifecycle.LiveData
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.model.weather.WeatherModel
import com.example.weatherapp.repositories.WeatherRepository

class MapViewModel(private val wRepository: WeatherRepository) : BaseViewModel() {

    fun getWeatherData(units: String, lat: String, lon: String): LiveData<WeatherModel> {
        return wRepository.getWeatherData(units, lat, lon)
    }

}