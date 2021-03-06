package com.kisanhub.demos.kisanhubdemo.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import timber.log.Timber
import java.util.*

const val ONE_SEC = 1000L

const val PERMISSION_REQUEST_CODE = 100
val PERMISSIONS = arrayOf(
    android.Manifest.permission.INTERNET,
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
    android.Manifest.permission.ACCESS_FINE_LOCATION
)

const val K_LOCATION_FILE = "k_location_preferences"
const val PREF_COUNTRY_KEY = "countryName"


const val METRICS_EXTRAS = "metric"
const val COUNTRY_EXTRAS = "countryName"

/***
 * checkForPermission(context: Activity) : This method request permission at runtime.
 * cautions: please make each permission present in AndroidManifest.xml
 */
fun checkForPermission(context: Activity) {
    Timber.d("CHECKING FOR PERMISSIONS")
    for (permission in PERMISSIONS) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, PERMISSIONS, PERMISSION_REQUEST_CODE)
        } else {
            Timber.d("${permission.toUpperCase()} GRANTED")
        }
    }
}

/***
 * getCountryNameFromLocation(context: Context, location: Location): String:
 * This method returns country name on according to country location.
 */
fun getCountryNameFromLocation(context: Context, location: Location): String {
    val addresses = Geocoder(context, Locale.getDefault()).getFromLocation(location.latitude, location.longitude, 3)
    return addresses[0].countryName
}



