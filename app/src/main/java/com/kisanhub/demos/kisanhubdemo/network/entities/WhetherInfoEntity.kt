package com.kisanhub.demos.kisanhubdemo.network.entities

import com.squareup.moshi.Json

data class WhetherInfoEntity(

    @Json(name = "month")
    val month: Int? = null,

    @Json(name = "year")
    val year: Int? = null,

    @Json(name = "value")
    val value: Double? = null
)