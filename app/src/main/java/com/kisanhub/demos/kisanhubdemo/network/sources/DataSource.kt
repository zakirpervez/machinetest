package com.kisanhub.demos.kisanhubdemo.network.sources

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WhetherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse

interface DataSource {
    fun getWhetherInfo(countryName: String, metrics: Metrics): LiveData<ApiResponse<List<WhetherInfoEntity>, String>>
}