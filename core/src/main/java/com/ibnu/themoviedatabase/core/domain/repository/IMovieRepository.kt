package com.ibnu.themoviedatabase.core.domain.repository

import androidx.paging.PagingData
import com.ibnu.themoviedatabase.core.data.Resource
import com.ibnu.themoviedatabase.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<PagingData<Movie>>>

    fun getSearchedMovie(keyword: String): Flow<Resource<PagingData<Movie>>>

    fun getFavoriteMovie(): Flow<PagingData<Movie>>

    suspend fun setFavoriteMovie(movie: Movie, state: Boolean)
}