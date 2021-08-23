package com.ibnu.themoviedatabase.core.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibnu.themoviedatabase.core.data.local.entities.MovieEntity
import com.ibnu.themoviedatabase.core.data.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<PagingData<MovieEntity>> = Pager(
        config = PagingConfig(15)
    ) {
        movieDao.getAllMovies()
    }.flow

    fun getFavoriteMovie() = Pager(
        config = PagingConfig(15)
    ) {
        movieDao.getAllFavoriteMovies()
    }.flow

    suspend fun insertAllMovie(movies: List<MovieEntity>) = movieDao.insertAllMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

}