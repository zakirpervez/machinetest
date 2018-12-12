package com.kisanhub.demos.kisanhubdemo

import com.codemonkeylabs.fpslibrary.TinyDancer
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class KisanHubDebugApplication : KisanHubApplication() {

    override fun onCreate() {
        super.onCreate()
        initialize()
    }


    private fun initialize(){
        Stetho.initializeWithDefaults(this)
        if (LeakCanary.isInAnalyzerProcess(this)) return
        else LeakCanary.install(this)
        TinyDancer.create()
            .redFlagPercentage(.1f) // set red indicator for 10%....different from default
            .startingXPosition(200)
            .startingYPosition(600)
            .show(this)

        Timber.plant(Timber.DebugTree())
    }

}