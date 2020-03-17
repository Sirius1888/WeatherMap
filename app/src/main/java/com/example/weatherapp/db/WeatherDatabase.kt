package com.example.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.city.CityModel
import com.example.weatherapp.model.city.FavoriteCities

@Database(entities = [FavoriteCities::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteCitiesDao

}