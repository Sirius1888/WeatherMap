package com.example.weatherapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.db.FavoriteCitiesDao
import com.example.weatherapp.model.city.FavoriteCities
import com.example.weatherapp.model.weather.WeatherModel
import com.example.weatherapp.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository(private val retrofit: RetrofitClient, private val dao: FavoriteCitiesDao) {
    private lateinit var api: WeatherApi

    fun getWeatherData(units: String, lat: String, lon: String): MutableLiveData<WeatherModel> {
        api = retrofit.retrofit(NetworkConstants.BASE_URL).create(WeatherApi::class.java)
        val data = MutableLiveData<WeatherModel>()
        api.getWeatherData(units, lat, lon, WEATHER_KEY)
            .enqueue(object : Callback<WeatherModel> {
                override fun onResponse(
                    call: Call<WeatherModel>,
                    response: Response<WeatherModel>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                    data.value = null
                }
            })
        return data
    }

    suspend fun insertToDb(lng: Double, lat: Double) {
        dao.insertFavorite(
            FavoriteCities(
                id = 1,
                latitude = lat,
                longitude = lng)
        )
    }

}