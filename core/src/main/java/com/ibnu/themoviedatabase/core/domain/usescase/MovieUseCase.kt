package com.ibnu.themoviedatabase.core.domain.usescase

import androidx.paging.PagingData
import com.ibnu.themoviedatabase.core.data.Resource
import com.ibnu.themoviedatabase.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<PagingData<Movie>>>
    fun getSearchedMovies(keyword: String): Flow<Resource<PagingData<Movie>>>
    fun getFavoriteMovies(): Flow<PagingData<Movie>>
    suspend fun setFavoriteMovie(movie: Movie, state: Boolean)
}