package com.kisanhub.demos.kisanhubdemo.network.factory

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

const val RETROFIT_ERROR = 101

object RetrofitFactory {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private fun getRetroInstance(url: String): Retrofit {
        return getBuilder(url)
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                okHttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    private fun getBuilder(url: String): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(url)
    }

    private fun okHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.d(message)
        })
    }
    
    fun <T> getService(url: String, clazz: Class<T>): T {
        return getRetroInstance(url).create(clazz)
    }

}