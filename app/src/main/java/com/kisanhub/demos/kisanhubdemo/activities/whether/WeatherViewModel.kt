package com.kisanhub.demos.kisanhubdemo.activities.whether

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.kisanhub.demos.kisanhubdemo.network.Repository
import com.kisanhub.demos.kisanhubdemo.network.entities.WhetherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.sources.Metrics
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse

class WeatherViewModel : ViewModel() {

    fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WhetherInfoEntity>, String>> = Repository.getWhetherInfo(countryName, metrics)


}