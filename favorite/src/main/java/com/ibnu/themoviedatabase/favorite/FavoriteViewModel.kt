package com.ibnu.themoviedatabase.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibnu.themoviedatabase.core.domain.usescase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getFavoriteMovies() = movieUseCase.getFavoriteMovies().asLiveData()
}