package com.kisanhub.demos.kisanhubdemo.activities.splash

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import com.kisanhub.demos.kisanhubdemo.R
import com.kisanhub.demos.kisanhubdemo.activities.BaseActivity
import com.kisanhub.demos.kisanhubdemo.activities.home.HomeActivity
import com.kisanhub.demos.kisanhubdemo.prefrences.KLocationPrefrences
import com.kisanhub.demos.kisanhubdemo.util.*

/***
 * Splash activity contain code to get location using wifi or GPS system
 * I am little bit confuse, As per doc you mention only 4 cities that's why I am writing location code here
 * If you want to current location country name, It is stored in shared preferences.
 */
class SplashActivity : BaseActivity(),
    KLocationCallback,
    LifecycleOwner {

    private lateinit var kLocation: KLocations
    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkForPermission(this)

        kLocation = KLocations(this@SplashActivity, this)
        lifecycleRegistry.addObserver(kLocation)

    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun location(location: Location) {
        val countryName = getAddressFromLocation(this@SplashActivity, location)
        if (countryName.isNotEmpty()) {
            KLocationPrefrences.setString(PREF_COUNTRY_KEY, countryName)
            launchActivity()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.isNotEmpty()) {
            var count = 0
            for (per in permissions) {
                if (ContextCompat.checkSelfPermission(this@SplashActivity, per)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    count += 1
                }
            }

            if (count > 0) {
                checkForPermission(this)
            } else {
                kLocation = KLocations(this@SplashActivity, this)
                lifecycleRegistry.addObserver(kLocation)
            }
        }
    }

    private fun launchActivity() {
        Handler().postDelayed({
            HomeActivity.start(this@SplashActivity)
            finish()
        }, ONE_SEC * 2)
    }
}
