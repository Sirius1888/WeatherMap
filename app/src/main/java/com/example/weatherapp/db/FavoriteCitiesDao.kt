package com.example.weatherapp.db

import androidx.room.*
import com.example.weatherapp.model.city.FavoriteCities

@Dao
interface FavoriteCitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(model: FavoriteCities)

    @Query("SELECT * FROM favorite")
    suspend fun getAllFavorite(): List<FavoriteCities>

}