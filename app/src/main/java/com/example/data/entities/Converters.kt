package com.example.data.entities

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromList(value: String): List<Int> {
        return try {
            value.trim().split(",").map { it.trim().toInt() }
        } catch (e: Exception){
            e.printStackTrace()
            emptyList()
        }
    }

    @TypeConverter
    fun listToString(value: List<Int>): String{
        return value.joinToString()
    }

}