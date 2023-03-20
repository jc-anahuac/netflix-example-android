package com.example.data.remote

import com.example.data.entities.MovieEntity
import com.example.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
): BaseDataSource() {

    suspend fun getPopularMovies(apiKey: String) = getResult { apiService.getPopularMovies(apiKey) }
}