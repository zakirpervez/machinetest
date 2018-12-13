package com.kisanhub.demos.kisanhubdemo.activities.whether.contract

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics

interface WeatherInfoContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun getWhetherInfo(liveData: LiveData<ApiResponse<List<WeatherInfoEntity>, String>>)
    }

    interface ViewModel {
        fun getWhetherInfo(
            countryName: String,
            metrics: Metrics
        )
    }
}