package com.example.weatherapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.city.CityModel
import com.example.weatherapp.network.CityApi
import com.example.weatherapp.network.NetworkConstants
import com.example.weatherapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Karukes Sergey on
 */
class CitiesRepository(private val retrofit: RetrofitClient) {
    private lateinit var api: CityApi

    fun getCityData(capital: String): MutableLiveData<MutableList<CityModel>> {
        api = retrofit.retrofit(NetworkConstants.BASE_CITY_URL).create(CityApi::class.java)
        val data = MutableLiveData<MutableList<CityModel>>()
        api.getCityData(capital)
            .enqueue(object : Callback<MutableList<CityModel>> {
                override fun onResponse(
                    call: Call<MutableList<CityModel>>,
                    response: Response<MutableList<CityModel>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<MutableList<CityModel>>, t: Throwable) {
                    data.value = null
                }
            })
        return data
    }

}