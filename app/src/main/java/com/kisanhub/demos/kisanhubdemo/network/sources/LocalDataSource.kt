package com.kisanhub.demos.kisanhubdemo.network.sources

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics

/***
 * This data source is used for JUnit and Instrumentation testing purpose.
 * Create and put Json File of each api responses and put it inside testing folders and use it.
 */
object LocalDataSource : DataSource {
    override fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WeatherInfoEntity>, String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}