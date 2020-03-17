package com.example.weatherapp.di

import androidx.room.Room
import com.example.weatherapp.db.WeatherDatabase
import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.repositories.CitiesRepository
import com.example.weatherapp.repositories.WeatherRepository
import com.example.weatherapp.ui.city.CityViewModel
import com.example.weatherapp.ui.map.MapViewModel
import com.example.weatherapp.ui.weather_bottom.WeatherBottomViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        RetrofitClient()
    }

    single {
        Room.databaseBuilder(androidApplication(), WeatherDatabase::class.java, "WEATHER_DB")
            .build()
    }

    single { get<WeatherDatabase>().favoriteDao }

    factory {
        WeatherRepository(get(), get())
    }

    factory {
        CitiesRepository(get())
    }

    viewModel {
        MapViewModel(get())
    }

    viewModel {
        CityViewModel(get())
    }

    viewModel {
        WeatherBottomViewModel(get())
    }

}