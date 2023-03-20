package com.example.data.entities;

import com.example.data.model.GetMoviesResponse

fun GetMoviesResponse.toMovieEntity(): List<MovieEntity>{
        return results.map {
                MovieEntity(
                        id = it.id,
                        adult = it.adult,
                        backdropPath = it.backDropPath,
                        genreIds = it.genreIds,
                        originalTitle = it.originalTitle,
                        originalName = it.originalLanguage,
                        overview = it.overview,
                        popularity = it.popularity,
                        posterPath = it.posterPath,
                        releaseDate = it.releaseDate,
                        title = it.title,
                        video = it.video,
                        voteAverage = it.voteAverage,
                        voteCount = it.voteCount
                )
        }
}