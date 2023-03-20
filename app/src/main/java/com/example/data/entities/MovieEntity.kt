package com.example.data.entities
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName= "movieEntity")
data class MovieEntity(
    @PrimaryKey
    val id:Long = 0,
    @SerializedName("adult")
    val adult:Boolean = false,
    @SerializedName("backdrop_path")
    var backdropPath:String? = "",
    @SerializedName("genre_ids")
    var genreIds: List<Int>,
    @SerializedName("original_title")
    var originalTitle:String? = "",
    @SerializedName("original_language")
    var originalName: String? = "",
    @SerializedName("overview")
    var overview:String? = "",
    @SerializedName("popoularity")
    var popularity: Double = 0.0,
    @SerializedName("poster_path")
    var posterPath:String? = "",
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("video")
    var video: Boolean? = false,
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int? = 0
)
