package com.kisanhub.demos.kisanhubdemo.prefrences

import android.content.SharedPreferences

interface IPreferences {
    //    fun initialize(context: Context, fileName:String)

    //    common operation
    fun getEditor(): SharedPreferences.Editor

    fun clearAll()
    fun remove(key: String)
    fun getAll(): Map<String, *>

    //    insertion
    fun setString(key: String, value: String)

    fun setInt(key: String, value: Int)
    fun setLong(key: String, value: Long)

    fun setFloat(key: String, value: Float)
    //    fun setDouble(key: String, value: Double)
    fun setBoolean(key: String, value: Boolean)

    fun setStringSet(key: String, value: MutableSet<String>)


    //    selection
    fun getString(key: String): String

    fun getInt(key: String): Int
    fun getLong(key: String): Long
    fun getFloat(key: String): Float
    //    fun getDouble(key: String): Double
    fun getBoolean(key: String): Boolean

    fun getStringSet(key: String): MutableSet<String>
}