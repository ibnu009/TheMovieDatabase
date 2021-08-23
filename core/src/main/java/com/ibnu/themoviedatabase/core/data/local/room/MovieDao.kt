package com.ibnu.themoviedatabase.core.data.local.room

import androidx.paging.PagingSource
import androidx.room.*
import com.ibnu.themoviedatabase.core.data.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE is_favorite = 1")
    fun getAllFavoriteMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movie where movie_id=:id")
    suspend fun getMovieDetail(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovies(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}