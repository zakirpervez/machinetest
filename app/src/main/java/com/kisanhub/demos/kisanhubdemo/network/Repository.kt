package com.kisanhub.demos.kisanhubdemo.network

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.sources.DataSource
import com.kisanhub.demos.kisanhubdemo.network.sources.RemoteDataSource
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics


object Repository : DataSource {
    override fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WeatherInfoEntity>, String>> = RemoteDataSource.getWhetherInfo(countryName, metrics)
}