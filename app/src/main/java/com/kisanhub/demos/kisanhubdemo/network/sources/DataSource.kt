package com.kisanhub.demos.kisanhubdemo.network.sources

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics

interface DataSource {
    fun getWhetherInfo(countryName: String, metrics: Metrics): LiveData<ApiResponse<List<WeatherInfoEntity>, String>>
}