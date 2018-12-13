package com.kisanhub.demos.kisanhubdemo

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

/***
 * Application classes are changes according to build variant selection.
 * Please refer debug inside src folder.
 * @link : KisanHubDebugApplication : is child of this class which become application class when you are selecting any build variant
 */
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