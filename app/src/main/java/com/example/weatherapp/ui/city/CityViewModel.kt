package com.example.weatherapp.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.model.city.CityModel
import com.example.weatherapp.repositories.CitiesRepository

class CityViewModel(private val cRepository: CitiesRepository) : BaseViewModel() {

    var cities: MutableLiveData<MutableList<CityModel>> = MutableLiveData()
    fun getCityData(city: String) {
        loading.value = false
        cities = cRepository.getCityData(city)
    }

}