package com.kisanhub.demos.kisanhubdemo.network.sources

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kisanhub.demos.kisanhubdemo.BuildConfig
import com.kisanhub.demos.kisanhubdemo.network.entities.WhetherInfoEntity
import com.kisanhub.demos.kisanhubdemo.network.factory.RETROFIT_ERROR
import com.kisanhub.demos.kisanhubdemo.network.factory.RetrofitFactory
import com.kisanhub.demos.kisanhubdemo.network.util.ApiResponse
import com.kisanhub.demos.kisanhubdemo.network.util.HTTPStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import timber.log.Timber

object RemoteDataSource : DataSource {


    override fun getWhetherInfo(
        countryName: String,
        metrics: Metrics
    ): LiveData<ApiResponse<List<WhetherInfoEntity>, String>> {
        val mutableData: MutableLiveData<ApiResponse<List<WhetherInfoEntity>, String>> = MutableLiveData()
        var requestCount = 0
        val url = buildUrl(countryName, metrics.getMetric())
        RetrofitFactory.getService(BuildConfig.BASE_URL, RestService::class.java)
            .getWhetherInfo(url)
            .enqueue(object : Callback<List<WhetherInfoEntity>> {
                override fun onFailure(call: Call<List<WhetherInfoEntity>>, t: Throwable) {
                    requestCount += 1
                    mutableData.value = getErrorResponse(RETROFIT_ERROR, call, this, requestCount)
                }

                override fun onResponse(
                    call: Call<List<WhetherInfoEntity>>,
                    response: Response<List<WhetherInfoEntity>>
                ) {
                    mutableData.value = when (response.code()) {
                        HTTPStatus.SUCCESS -> ApiResponse(response.body(), null)
                        else -> {
                            requestCount += 1
                            getErrorResponse(response.code(), call, this, requestCount)
                        }
                    }
                }

            })
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

}


interface RestService {
    @GET()
    fun getWhetherInfo(@Url url: String): Call<List<WhetherInfoEntity>>

}


enum class Metrics {
    RAINFALL {
        override fun getMetric(): String {
            return "Rainfall"
        }
    },
    TMAX {
        override fun getMetric(): String {
            return "Tmax"
        }
    },
    TMIN {
        override fun getMetric(): String {
            return "Tmin"
        }
    };

    abstract fun getMetric(): String
}

