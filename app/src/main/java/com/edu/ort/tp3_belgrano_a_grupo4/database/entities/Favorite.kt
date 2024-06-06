package com.edu.ort.tp3_belgrano_a_grupo4.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val id: Int,
    val isFavorite: Boolean
)

