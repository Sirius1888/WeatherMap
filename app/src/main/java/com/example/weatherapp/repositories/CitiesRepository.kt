package com.example.weatherapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.city.CityModel
import com.example.weatherapp.network.Resource
import com.example.weatherapp.network.ResponseHandler
import com.example.weatherapp.network.RetrofitClient


/**
 * Created by Karukes Sergey on
 */
class CitiesRepository(private val retrofit: RetrofitClient) {

    fun getCityData(capital: String): Resource<List<CityModel>> {
        return try {
            ResponseHandler().handleSuccess(retrofit.citiesApi.getCityData(capital))
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

}
