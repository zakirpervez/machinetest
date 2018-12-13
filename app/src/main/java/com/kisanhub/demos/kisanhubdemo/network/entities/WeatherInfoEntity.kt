package com.kisanhub.demos.kisanhubdemo.network.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "WeatherInfo")
data class WeatherInfoEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "metric")
    var metric: String? = null,

    @ColumnInfo(name = "country")
    var country: String? = null,

    @ColumnInfo(name = "month")
    @Json(name = "month")
    var month: Int? = null,

    @ColumnInfo(name = "year")
    @Json(name = "year")
    var year: Int? = null,

    @ColumnInfo(name = "value")
    @Json(name = "value")
    var value: Double? = null
)