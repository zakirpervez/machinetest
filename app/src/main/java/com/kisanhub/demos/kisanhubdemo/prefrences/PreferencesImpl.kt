package com.kisanhub.demos.kisanhubdemo.prefrences

import android.content.Context
import android.content.SharedPreferences
import com.kisanhub.demos.kisanhubdemo.KisanHubApplication

/**
 * Created by Zakir Pervez on 27/4/18.
 */
abstract class PreferencesImpl(fileName: String) : IPreferences {

    private var mPreferences: SharedPreferences =
        KisanHubApplication.kContext!!.getSharedPreferences(fileName, Context.MODE_PRIVATE)


    override fun getEditor(): SharedPreferences.Editor {
        return mPreferences.edit()
    }

    override fun clearAll() {
        getEditor().clear().apply()
    }

    override fun remove(key: String) {
        getEditor().remove(key).apply()
    }

    override fun getAll(): Map<String, *> {
        return mPreferences.all
    }

    override fun setString(key: String, value: String) {
        getEditor().putString(key, KCipher.encrypt(value)).apply()
    }

    override fun setInt(key: String, value: Int) {
        getEditor().putString(key, KCipher.encryptInt(value)).apply()
//        getEditor().putInt(key, InStoreCipher.encryptInt(value)).apply()
    }

    override fun setLong(key: String, value: Long) {
//        getEditor().putLong(key, value).apply()
        getEditor().putString(key, KCipher.encryptLong(value)).apply()
    }

    override fun setFloat(key: String, value: Float) {
//        getEditor().putFloat(key, value).apply()
        getEditor().putString(key, KCipher.encryptFloat(value)).apply()
    }

//    override fun setDouble(key: String, value: Double) {
//        getEditor().put
//    }

    override fun setBoolean(key: String, value: Boolean) {
//        getEditor().putBoolean(key, value)
        getEditor().putString(key, KCipher.encryptBoolean(value)).apply()
    }

    override fun setStringSet(key: String, value: MutableSet<String>) {
        getEditor().putStringSet(key, value)
    }

    override fun getString(key: String): String {
//        return mPreferences.getString(key, "")
        if (mPreferences.getString(key, "").isNullOrEmpty()) return ""
        return KCipher.decrypt(mPreferences.getString(key, ""))
    }

    override fun getInt(key: String): Int {
//        return mPreferences.getInt(key, 0)
        if (mPreferences.getString(key, "").isNullOrEmpty()) return 0
        return KCipher.decryptInt(mPreferences.getString(key, ""))
    }

    override fun getLong(key: String): Long {
//        return mPreferences.getLong(key, 0)
        return KCipher.decryptLong(mPreferences.getString(key, ""))
    }

    override fun getFloat(key: String): Float {
//        return mPreferences.getFloat(key, 0.0f)
        return KCipher.decryptFloat(mPreferences.getString(key, ""))
    }

//    override fun getDouble(key: String): Double {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun getBoolean(key: String): Boolean {
//        return mPreferences.getBoolean(key, false)
        return KCipher.decryptBoolean(mPreferences.getString(key, ""))
    }

    override fun getStringSet(key: String): MutableSet<String> {
        return mPreferences.getStringSet(key, null)
    }
}