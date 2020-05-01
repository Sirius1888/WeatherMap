package com.example.weatherapp.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.model.city.CityModel
import com.example.weatherapp.repositories.CitiesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CityViewModel(private val cRepository: CitiesRepository) : BaseViewModel() {

    var cities: MutableLiveData<List<CityModel>?> = MutableLiveData()
    fun getCityData(city: String) {
        cities.value = cRepository.getCityData(city).data
    }

}