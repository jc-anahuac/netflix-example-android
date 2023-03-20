package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genreEntity")
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)