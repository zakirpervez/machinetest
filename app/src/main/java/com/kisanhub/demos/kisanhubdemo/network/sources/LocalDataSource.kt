package com.kisanhub.demos.kisanhubdemo.network.sources

import android.arch.lifecycle.LiveData
import com.kisanhub.demos.kisanhubdemo.network.entities.WhetherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse

/***
 * This data source is used for JUnit and Instrumentation testing purpose.
 * Create and put Json File of each api responses and put it inside testing folders and use it.
 */
object LocalDataSource : DataSource {
    override fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WhetherInfoEntity>, String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}