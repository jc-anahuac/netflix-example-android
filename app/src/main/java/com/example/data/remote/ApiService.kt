package com.example.data.remote

import com.example.data.model.GetGenreResponse
import com.example.data.model.GetMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<GetMoviesResponse>

    @GET("genre/movie/list")
    suspend fun getGenreMovies(@Query("api_key") apiKey: String): Response<GetGenreResponse>
}