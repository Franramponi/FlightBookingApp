package com.edu.ort.tp3_belgrano_a_grupo4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.ort.tp3_belgrano_a_grupo4.database.dao.FavoriteDao
import com.edu.ort.tp3_belgrano_a_grupo4.database.dao.NightModeSettingsDao
import com.edu.ort.tp3_belgrano_a_grupo4.database.entities.Favorite
import com.edu.ort.tp3_belgrano_a_grupo4.database.entities.NightModeSettings

@Database(entities = [NightModeSettings::class, Favorite::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nightModeSettingsDao(): NightModeSettingsDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val DB_NAME = "app_database"
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}