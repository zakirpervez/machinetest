package com.kisanhub.demos.kisanhubdemo.network.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity


@Dao
interface KDao {
    @Insert
    fun insertAll(entities: List<WeatherInfoEntity>)

    @Query("Select * FROM  WeatherInfo")
    fun selectAll(): List<WeatherInfoEntity>

    @Query("SELECT * FROM WeatherInfo WHERE metric=:metricStr AND country=:countryStr")
    fun selectAll(countryStr: String, metricStr: String): List<WeatherInfoEntity>

    @Query("DELETE FROM WeatherInfo")
    fun deleteAll()

    @Query("UPDATE WeatherInfo SET metric = :metricStr AND country = :countryStr")
    fun update(countryStr: String, metricStr: String): Int
}