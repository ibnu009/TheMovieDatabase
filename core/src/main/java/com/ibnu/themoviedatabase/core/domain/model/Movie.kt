package com.ibnu.themoviedatabase.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val movieName: String,
    val movieCategory: List<Int>,
    val movieBackdropPath: String?,
    val moviePosterPath: String?,
    val movieDescription: String,
    val movieAverageRating: Double,
    val movieVoteCount: Int,
    var isFavorite: Boolean
) : Parcelable
