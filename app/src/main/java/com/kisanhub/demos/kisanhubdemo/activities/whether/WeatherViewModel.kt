package com.kisanhub.demos.kisanhubdemo.activities.whether

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kisanhub.demos.kisanhubdemo.network.Repository
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics

class WeatherViewModel : ViewModel() {

    fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WeatherInfoEntity>, String>> = Repository.getWhetherInfo(countryName, metrics)


}