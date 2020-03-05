package com.example.weatherapp.model

data class WeatherMainModel(val city: City?,
                            val cnt: Int? = 0,
                            val cod: String? = "",
                            val message: Int? = 0,
                            val list: List<ListItem>?)