package com.kisanhub.demos.kisanhubdemo.network.sources

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kisanhub.demos.kisanhubdemo.BuildConfig
import com.kisanhub.demos.kisanhubdemo.network.database.KDatabase
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.factory.RETROFIT_ERROR
import com.kisanhub.demos.kisanhubdemo.network.factory.RestService
import com.kisanhub.demos.kisanhubdemo.network.factory.RetrofitFactory
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.HTTPStatus
import com.kisanhub.demos.kisanhubdemo.network.util.Metrics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

object RemoteDataSource : DataSource {
    private val kDatabase: KDatabase = KDatabase.getInstance()
    val weatherInfoDao = kDatabase.getDao()

    override fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WeatherInfoEntity>, String>> {
        val mutableData: MutableLiveData<ApiResponse<List<WeatherInfoEntity>, String>> = MutableLiveData()
        if (isCallBefore(countryName, metrics.getMetric())) {
            Timber.d("***************DATA FETCHING FROM DATABASE***************")
            mutableData.value = ApiResponse(weatherInfoDao.selectAll(countryName, metrics.getMetric()), null)
        } else {
            Timber.d("***************FIRST CALL ALWAYS FROM API***************")
            Timber.d("***************DATA FETCHING FROM API***************")
            var requestCount = 0
            val url = buildUrl(countryName, metrics.getMetric())
            RetrofitFactory.getService(BuildConfig.BASE_URL, RestService::class.java)
                .getWhetherInfo(url)
                .enqueue(object : Callback<List<WeatherInfoEntity>> {
                    override fun onFailure(call: Call<List<WeatherInfoEntity>>, t: Throwable) {
                        requestCount += 1
                        mutableData.value = getErrorResponse(RETROFIT_ERROR, call, this, requestCount)
                    }

                    override fun onResponse(
                        call: Call<List<WeatherInfoEntity>>,
                        response: Response<List<WeatherInfoEntity>>
                    ) {
                        mutableData.value = when (response.code()) {
                            HTTPStatus.SUCCESS -> {

                                Timber.d("***************INSERTING DATA***************")
                                weatherInfoDao.insertAll(
                                    updateWeatherInfo(
                                        countryName,
                                        metrics.getMetric(),
                                        response.body()!!
                                    )
                                )
                                Timber.d("***************INSERTING DATA COMPLETE***************")
                                ApiResponse(response.body(), null)
                            }
                            else -> {
                                requestCount += 1
                                getErrorResponse(response.code(), call, this, requestCount)
                            }
                        }
                    }

                })
        }

        return mutableData
    }


    private fun buildUrl(countryName: String, metrics: String): String {
        return StringBuffer("metoffice")
            .append("/")
            .append(metrics)
            .append("-")
            .append(countryName)
            .append(".json")
            .toString()
    }

    private fun <R> getErrorResponse(
        statusCode: Int,
        call: Call<R>,
        callback: Callback<R>,
        requestCount: Int
    ): ApiResponse<R, String> {
        var errorMessage: String? = ""
        if (requestCount < 4) {
            errorMessage = when (statusCode) {
                HTTPStatus.BAD_REQUEST -> "Bad Request, ERROR_CODE: $statusCode"
                HTTPStatus.REQUEST_TIME_OUT -> "Request time out, ERROR_CODE: $statusCode"
                HTTPStatus.UNAUTHORIZED -> "Unauthorized user, ERROR_CODE: $statusCode"
                HTTPStatus.FORBIDDEN -> "Forbidden, ERROR_CODE: $statusCode"
                HTTPStatus.PAGE_NOT_FOUND -> "Page not found, ERROR_CODE: $statusCode"
                HTTPStatus.INTERNAL_SERVER_ERROR -> "Internal serve error, ERROR_CODE: $statusCode"
                RETROFIT_ERROR -> "Retrofit Error, ERROR_CODE: $statusCode"
                else -> "Something went wrong, ERROR_CODE: $statusCode"
            }
            Timber.d(errorMessage)
            call.clone().enqueue(callback)
        }
        return ApiResponse(null, errorMessage)
    }


    private fun updateWeatherInfo(
        countryName: String,
        metrics: String,
        entities: List<WeatherInfoEntity>
    ): List<WeatherInfoEntity> {
        for (entity in entities) {
            entity.apply {
                country = countryName
                metric = metrics
            }
        }
        return entities
    }

    private fun isCallBefore(countryName: String, metrics: String): Boolean {
        return weatherInfoDao.selectAll(countryName, metrics).isNotEmpty()
    }
}






