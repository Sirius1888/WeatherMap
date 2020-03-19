package com.example.weatherapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseMapFragment
import com.example.weatherapp.ui.weather_bottom.WeatherBottomSheet
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseMapFragment(R.layout.fragment_map) {

    private val viewModel: MapViewModel by viewModel()
    private lateinit var weatherBottomSheet: BottomSheetBehavior<View>
    private var isDisplayingMarkers = true

    private fun initBottomSheet(it: Marker) {
        val bottomWeather =
            WeatherBottomSheet()
        if (!bottomWeather.isAdded) bottomWeather.show(activity!!.supportFragmentManager, bottomWeather.tag)
    }

    private fun getWeather() {
        viewModel.getWeatherData("metrics", "35", "139")
    }

    private fun setupMap() {
        val mapFragment = SupportMapFragment.newInstance()
        fragmentManager?.beginTransaction()?.add(R.id.map_container, mapFragment)?.commit()
        mapFragment.getMapAsync(this)
    }

    override fun onCreateMethod() {
        setupMap()
        getWeather()

        click()
    }

    override fun initMap() {
        mMap.setOnInfoWindowClickListener {
            initBottomSheet(it)
        }

        mMap.setOnMapClickListener {
            Toast.makeText(
                activity?.applicationContext,
                "LAT: ${it.latitude} LNG ${it.longitude}",
                Toast.LENGTH_LONG
            ).show()
            addMarkerToMap(it)
        }    }

    private fun click() {
        isDisplayingMarkers = false
    }

    private fun addMarkerToMap(location: LatLng) {
        if (isDisplayingMarkers) mMap.clear()
        val markerOptions = MarkerOptions()
            .position(location)
            .title("Marker")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
        mMap.addMarker(markerOptions).showInfoWindow()
    }

    private fun displayWeather(it: Marker) {
        weatherBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
    }

}
