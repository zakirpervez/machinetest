package com.kisanhub.demos.kisanhubdemo.network.sources

/***
 * Data Source : As its name describe it, It is a <b>Source</b> to local and network data.
 * @link: RemoteDataSource, LocalDataSource
 * here RemoteDataSource is actual data source who responsible to provide data from api.
 * While LocalDataSource created for testing purpose.
 * If you making an application test driven please use LocalDataSource instead of RemoteDataSource
 */

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics

interface DataSource {
    fun getWhetherInfo(countryName: String, metrics: Metrics): LiveData<ApiResponse<List<WeatherInfoEntity>, String>>
}