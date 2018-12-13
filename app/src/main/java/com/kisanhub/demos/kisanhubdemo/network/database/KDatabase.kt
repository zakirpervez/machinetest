package com.kisanhub.demos.kisanhubdemo.network.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.kisanhub.demos.kisanhubdemo.KisanHubApplication
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity


private const val DB_NAME = "kisan.db"

@Database(entities = [WeatherInfoEntity::class], version = 1, exportSchema = false)
abstract class KDatabase : RoomDatabase() {

    abstract fun getDao(): KDao

    companion object {

        private var instance: KDatabase? = null

        fun getInstance(): KDatabase {
            if (instance == null) {
                synchronized(KDatabase::class) {
                    instance = Room.databaseBuilder(
                        KisanHubApplication.kContext!!,
                        KDatabase::class.java, DB_NAME
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build()
                }
            }

            return instance!!
        }

        fun dropDb() {
            instance = null
        }

    }


}