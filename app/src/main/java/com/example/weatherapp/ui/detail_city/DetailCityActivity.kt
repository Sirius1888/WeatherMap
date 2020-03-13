package com.example.weatherapp.ui.detail_city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_detail_city.*

class DetailCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_city)

        val cityFlag = intent.getStringExtra("city")

        Glide
            .with(this)
            .load(cityFlag)
            .into(city_flags)
    }

}

