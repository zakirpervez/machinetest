package com.kisanhub.demos.kisanhubdemo.network.util

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

    companion object {
        fun getMetric(metric: String): Metrics {
            return when (metric) {
                RAINFALL.getMetric() -> RAINFALL
                TMAX.getMetric() -> TMAX
                TMIN.getMetric() -> TMIN
                else -> TMAX
            }
        }
    }

    abstract fun getMetric(): String




}