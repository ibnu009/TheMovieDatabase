package com.ibnu.themoviedatabase.core.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("results")
    val result: List<MovieResponse>,
    @field:SerializedName("total_pages")
    val totalPage: Int
)