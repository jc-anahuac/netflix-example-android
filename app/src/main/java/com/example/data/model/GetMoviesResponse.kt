package com.example.data.model

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    val page: Int,
    val results: List<GetMovieEntityResponse>
) {
    data class GetMovieEntityResponse(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backDropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        val id: Long,
        @SerializedName("original_language")
        val originalLanguage: String,
        val originalTitle: String,
        @SerializedName("original_title")
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}