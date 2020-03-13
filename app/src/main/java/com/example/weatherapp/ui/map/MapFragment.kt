package com.example.weatherapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel()
    private lateinit var weatherBottomSheet: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_map, container, false)
//        initBottomSheet()
        setupMap()
        getWeather()
        return view
    }

    private fun initBottomSheet(it: Marker) {
        val bottomWeather = WeatherBottomSheet()
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

    private fun addMarkerToMap(location: LatLng) {
        mMap.clear()
        val markerOptions = MarkerOptions()
            .position(location)
            .title("My marker")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
        mMap.addMarker(markerOptions).showInfoWindow()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraMoveListener(this)
        mMap.setOnCameraIdleListener(this)
        mMap.setOnMapClickListener {
            Toast.makeText(
                activity?.applicationContext,
                "LAT: ${it.latitude} LNG ${it.longitude}",
                Toast.LENGTH_LONG
            ).show()
            addMarkerToMap(it)
        }
        mMap.setOnInfoWindowClickListener {
            initBottomSheet(it)
        }
    }

    private fun displayWeather(it: Marker) {
        weatherBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED

    }

    override fun onCameraMove() {
    }

    override fun onCameraIdle() {
    }
}
