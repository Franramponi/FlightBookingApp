package com.edu.ort.tp3_belgrano_a_grupo4.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.edu.ort.tp3_belgrano_a_grupo4.database.entities.NightModeSettings

@Dao
interface NightModeSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNightModeSettings(nightMode: NightModeSettings)
    @Query("SELECT isNightModeEnabled FROM night_mode LIMIT 1")
    suspend fun getNightModeEnabled(): Boolean?
    @Update
    suspend fun updateNightModeSettings(nightMode: NightModeSettings)

    @Delete
    suspend fun deleteNightModeSettings(nightMode: NightModeSettings)
}