package com.example.data.model

data class GetGenreResponse(
    val genres: List<GenreItemResponse>
) {

    data class GenreItemResponse(
        val id: Int,
        val name: String
    )
}