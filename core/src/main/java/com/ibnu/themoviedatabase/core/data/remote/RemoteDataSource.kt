package com.ibnu.themoviedatabase.core.data.remote

import com.ibnu.themoviedatabase.core.data.remote.network.ApiResponse
import com.ibnu.themoviedatabase.core.data.remote.network.MovieService
import com.ibnu.themoviedatabase.core.data.remote.response.movie.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getAllMovie(language: String, page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = movieService.getTopRatedMovies(language, page)
                val data = response.result
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e("$e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchedMovie(
        language: String,
        keyword: String,
        page: Int,
        includeAdult: Boolean
    ): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = movieService.getSearchedMovies(language, keyword, page, includeAdult)
                val data = response.result
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e("$e")
            }
        }.flowOn(Dispatchers.IO)
    }


}