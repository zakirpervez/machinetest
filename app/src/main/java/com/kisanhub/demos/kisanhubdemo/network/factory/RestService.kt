package com.kisanhub.demos.kisanhubdemo.network.factory

import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RestService {
    @GET()
    fun getWhetherInfo(@Url url: String): Call<List<WeatherInfoEntity>>
}