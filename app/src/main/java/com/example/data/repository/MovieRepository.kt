package com.example.data.repository

import com.example.data.Local.dao.MovieDao
import com.example.data.entities.toMovieEntity
import com.example.data.remote.RemoteDataSource
import com.example.utils.performGetOperation
import java.time.chrono.ChronoLocalDate
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: MovieDao,
    private val remoteDataSource: RemoteDataSource){

    fun getPopularMovies(apiKey: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies()},
        networkCall = {remoteDataSource.getPopularMovies(apiKey)},
        saveCallResult = {localDataSource.insertAll(it.toMovieEntity())}
    )

    fun findById(id: Long) = localDataSource.findById(id)

    fun getLocalMovies() = localDataSource.getAllMovies()
}