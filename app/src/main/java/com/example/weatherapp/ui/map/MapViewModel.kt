package com.example.weatherapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.weather.WeatherModel
import com.example.weatherapp.repositories.WeatherRepository

class MapViewModel(private val wRepository: WeatherRepository) : ViewModel() {

    private var loading: Boolean = false
    fun getWeatherData(units: String, lat: String, lon: String): LiveData<WeatherModel> {
        return wRepository.getWeatherData(units, lat, lon)
    }

}