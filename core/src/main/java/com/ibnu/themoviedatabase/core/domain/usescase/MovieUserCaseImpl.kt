package com.ibnu.themoviedatabase.core.domain.usescase

import androidx.paging.PagingData
import com.ibnu.themoviedatabase.core.data.Resource
import com.ibnu.themoviedatabase.core.domain.model.Movie
import com.ibnu.themoviedatabase.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUserCaseImpl @Inject constructor(private val repository: IMovieRepository) :
    MovieUseCase {

    override fun getAllMovies(): Flow<Resource<PagingData<Movie>>> = repository.getAllMovie()

    override fun getSearchedMovies(keyword: String): Flow<Resource<PagingData<Movie>>> =
        repository.getSearchedMovie(keyword)

    override fun getFavoriteMovies(): Flow<PagingData<Movie>> = repository.getFavoriteMovie()

    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) =
        repository.setFavoriteMovie(movie, state)

}