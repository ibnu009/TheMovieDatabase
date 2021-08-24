package com.ibnu.themoviedatabase.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.themoviedatabase.core.domain.model.Movie
import com.ibnu.themoviedatabase.core.domain.usescase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModel() {
    fun setFavoriteMovie(movie: Movie, status: Boolean) {
        viewModelScope.launch {
            movieUseCase.setFavoriteMovie(movie, status)
        }
    }
}