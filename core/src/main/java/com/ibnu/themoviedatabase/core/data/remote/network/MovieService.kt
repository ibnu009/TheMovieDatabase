package com.ibnu.themoviedatabase.core.data.remote.network

import com.ibnu.themoviedatabase.core.data.remote.response.movie.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ListMovieResponse

    @GET("/3/search/movie")
    suspend fun getSearchedMovies(
        @Query("language") language: String,
        @Query("query") keyword: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean
    ): ListMovieResponse

}