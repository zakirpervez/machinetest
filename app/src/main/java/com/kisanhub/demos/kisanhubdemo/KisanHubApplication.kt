package com.kisanhub.demos.kisanhubdemo

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

open class KisanHubApplication : Application() {


    companion object {
        @JvmStatic
        var kContext: KisanHubApplication? = null
    }

    override fun onCreate() {
        kContext = this@KisanHubApplication
        super.onCreate()
        initialize()
    }


    private fun initialize() {
        Fresco.initialize(this)
    }

}