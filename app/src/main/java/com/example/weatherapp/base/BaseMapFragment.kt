package com.example.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback


abstract class BaseMapFragment<ViewModel: BaseViewModel>(private val layout: Int) : Fragment(), OnMapReadyCallback,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener {

    lateinit var mMap: GoogleMap

    protected abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(layout, container, false)
        onCreateMethod()

        return view
    }

    abstract fun onCreateMethod()
    abstract fun initMap()

    override fun onCameraMove() {}

    override fun onCameraIdle() {}

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraMoveListener(this)
        mMap.setOnCameraIdleListener(this)
        initMap()
    }
}