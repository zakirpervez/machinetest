package com.kisanhub.demos.kisanhubdemo.network

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WhetherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.sources.DataSource
import com.kisanhub.demos.kisanhubdemo.network.sources.Metrics
import com.kisanhub.demos.kisanhubdemo.network.sources.RemoteDataSource
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse


object Repository : DataSource {
    override fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WhetherInfoEntity>, String>> = RemoteDataSource.getWhetherInfo(countryName, metrics)
}