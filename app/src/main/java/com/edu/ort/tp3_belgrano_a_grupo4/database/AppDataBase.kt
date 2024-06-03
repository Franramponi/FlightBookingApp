package com.edu.ort.tp3_belgrano_a_grupo4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.ort.tp3_belgrano_a_grupo4.database.dao.NightModeSettingsDao
import com.edu.ort.tp3_belgrano_a_grupo4.database.entities.NightModeSettings

@Database(entities = [NightModeSettings::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nightModeSettingsDao(): NightModeSettingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val NIGHT_MODE_KEY = "night_mode_db"
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    NIGHT_MODE_KEY
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}