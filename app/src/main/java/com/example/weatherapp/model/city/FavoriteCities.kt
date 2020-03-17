package com.example.weatherapp.model.city

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteCities(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var latitude: Double,
    var longitude: Double
)
