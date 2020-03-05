package com.example.weatherapp.model

data class ListItem(val dt: Int? = 0,
                    val dtTxt: String? = "",
                    val weather: List<WeatherItem>??,
                    val main: Main?,
                    val clouds: Clouds?,
                    val sys: Sys?,
                    val wind: Wind?)