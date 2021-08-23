package com.ibnu.themoviedatabase.core.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "movie_name")
    val movieName: String,
    @ColumnInfo(name = "movie_category")
    val movieCategory: List<Int>,
    @ColumnInfo(name = "backdrop_path")
    val movieBackdropPath: String?,
    @ColumnInfo(name = "movie_image")
    val moviePosterPath: String?,
    @ColumnInfo(name = "description")
    val movieDescription: String,
    @ColumnInfo(name = "movie_average_rating")
    val movieAverageRating: Double,
    @ColumnInfo(name = "movie_vote_count")
    val movieVoteCount: Int,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
)