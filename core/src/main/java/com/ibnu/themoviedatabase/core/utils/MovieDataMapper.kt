package com.ibnu.themoviedatabase.core.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.ibnu.themoviedatabase.core.data.local.entities.MovieEntity
import com.ibnu.themoviedatabase.core.data.remote.response.movie.MovieResponse
import com.ibnu.themoviedatabase.core.domain.model.Movie

object MovieDataMapper {

    fun mapResponseToEntity(input: List<MovieResponse>): List<MovieEntity> =
        input.map {
            MovieEntity(
                movieId = it.movieId,
                movieName = it.movieName,
                movieAverageRating = it.movieAverageRating,
                movieCategory = it.movieCategory,
                movieBackdropPath = it.movieBackdropPath,
                moviePosterPath = it.moviePosterPath,
                movieDescription = it.movieDescription,
                movieVoteCount = it.movieVoteCount,
                isFavorite = false
            )
    }


    fun mapEntitiesToDomains(input: PagingData<MovieEntity>): PagingData<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                movieName = it.movieName,
                movieAverageRating = it.movieAverageRating,
                movieCategory = it.movieCategory,
                movieBackdropPath = it.movieBackdropPath,
                moviePosterPath = it.moviePosterPath,
                movieDescription = it.movieDescription,
                movieVoteCount = it.movieVoteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        movieName = input.movieName,
        movieAverageRating = input.movieAverageRating,
        movieCategory = input.movieCategory,
        movieBackdropPath = input.movieBackdropPath,
        moviePosterPath = input.moviePosterPath,
        movieDescription = input.movieDescription,
        movieVoteCount = input.movieVoteCount,
        isFavorite = input.isFavorite
    )

}