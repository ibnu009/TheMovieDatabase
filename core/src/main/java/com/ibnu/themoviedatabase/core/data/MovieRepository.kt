package com.ibnu.themoviedatabase.core.data

import androidx.paging.PagingData
import com.ibnu.themoviedatabase.core.data.local.LocalDataSource
import com.ibnu.themoviedatabase.core.data.remote.RemoteDataSource
import com.ibnu.themoviedatabase.core.data.remote.network.ApiResponse
import com.ibnu.themoviedatabase.core.data.remote.response.movie.MovieResponse
import com.ibnu.themoviedatabase.core.domain.model.Movie
import com.ibnu.themoviedatabase.core.domain.repository.IMovieRepository
import com.ibnu.themoviedatabase.core.utils.ConstValue.FIRST_PAGE
import com.ibnu.themoviedatabase.core.utils.ConstValue.TMDB_LANGUAGE
import com.ibnu.themoviedatabase.core.utils.MovieDataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<PagingData<Movie>>> =
        object : NetworkBoundResource<PagingData<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<PagingData<Movie>> =
                localDataSource.getAllMovie().map {
                    MovieDataMapper.mapEntitiesToDomains(it)
                }

            override fun shouldFetch(data: PagingData<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDatasource.getAllMovie(
                    language = TMDB_LANGUAGE,
                    FIRST_PAGE
                )

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponseToEntity(data)
                localDataSource.insertAllMovie(movieList)
            }
        }.asFlow()

    override fun getSearchedMovie(keyword: String): Flow<Resource<PagingData<Movie>>> =
        object : NetworkBoundResource<PagingData<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<PagingData<Movie>> =
                localDataSource.getAllMovie().map {
                    MovieDataMapper.mapEntitiesToDomains(it)
                }

            override fun shouldFetch(data: PagingData<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDatasource.getSearchedMovie(
                    language = TMDB_LANGUAGE,
                    keyword = keyword,
                    page = FIRST_PAGE,
                    includeAdult = false
                )

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponseToEntity(data)
                localDataSource.insertAllMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<PagingData<Movie>> =
        localDataSource.getFavoriteMovie().map {
            MovieDataMapper.mapEntitiesToDomains(it)
        }

    override suspend fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = MovieDataMapper.mapDomainToEntity(movie)
        withContext(Dispatchers.IO) {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }

}