package com.example.weatherapp.ui.weather_bottom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repositories.WeatherRepository
import kotlinx.coroutines.launch


class WeatherBottomViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun addToFavorite(lng: Double, lat: Double) {
        viewModelScope.launch {
            weatherRepository.insertToDb(lng, lat)
        }
    }


}
