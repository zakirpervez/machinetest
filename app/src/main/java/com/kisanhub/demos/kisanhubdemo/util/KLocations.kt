package com.kisanhub.demos.kisanhubdemo.util

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat

/***
 * KLocation : It is custom lifecycle aware component.
 * It is use to store current location of the user
 * Note : In future if you need to get weather information of current location it may be helpful.
 */
class KLocations(private val activity: Activity, private val callback: KLocationCallback) :
    LocationListener, LifecycleObserver {

    private lateinit var locationManager: LocationManager
    private var location: Location? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE
        val provider = locationManager.getBestProvider(criteria, true)


        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            location = locationManager.getLastKnownLocation(provider)
            if (location == null) {
                locationManager.requestLocationUpdates(provider, ONE_SEC, 0f, this@KLocations)
            } else {
                callback.location(location!!)
            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopUpdate() {
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        callback.location(location)
        stopUpdate()
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

}