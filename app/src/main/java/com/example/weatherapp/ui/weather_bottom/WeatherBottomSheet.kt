package com.example.weatherapp.ui.weather_bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseBottomSheet
import com.example.weatherapp.ui.weather_bottom.WeatherBottomViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherBottomSheet: BaseBottomSheet() {

    private var lng: Double = 0.0
    private var lat: Double = 0.0
    private val viewModel: WeatherBottomViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_weather, container, false)
        viewModel.addToFavorite(lng, lat)
        return view
    }



}