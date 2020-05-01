package com.example.weatherapp.network

import com.example.weatherapp.network.NetworkConstants.Companion.BASE_CITY_URL
import com.example.weatherapp.network.NetworkConstants.Companion.BASE_URL
import com.example.weatherapp.repositories.CitiesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val httplLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient().newBuilder().addInterceptor(httplLogging)
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .build()

    fun retrofit(url: String): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val citiesApi: CityApi = retrofit(BASE_CITY_URL).create(CityApi::class.java)
}