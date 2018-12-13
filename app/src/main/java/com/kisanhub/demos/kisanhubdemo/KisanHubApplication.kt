package com.kisanhub.demos.kisanhubdemo

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

open class KisanHubApplication : Application() {


    companion object {
        @JvmStatic
        var kContext: Context? = null
    }

    override fun onCreate() {
        kContext = applicationContext
        super.onCreate()
        initialize()
    }


    private fun initialize() {
        Fresco.initialize(this)
    }

}