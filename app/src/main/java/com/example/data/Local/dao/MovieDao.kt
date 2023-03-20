package com.example.data.Local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntity")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieEntity m WHERE m.id = :id")
    fun findById(id: Long): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)
}