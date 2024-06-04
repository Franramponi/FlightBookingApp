package com.edu.ort.tp3_belgrano_a_grupo4.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "night_mode")
data class NightModeSettings(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "isNightModeEnabled") val isNightModeEnabled: Boolean= false // Valor por defecto
)