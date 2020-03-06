package com.example.weatherapp.di

import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.repositories.WeatherRepository
import com.example.weatherapp.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        RetrofitClient()
    }

    single {
        WeatherRepository()
    }

    viewModel {
        MapViewModel(get())
    }

}