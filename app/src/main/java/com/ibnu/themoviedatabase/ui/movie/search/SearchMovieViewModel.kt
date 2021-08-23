package com.ibnu.themoviedatabase.ui.movie.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibnu.themoviedatabase.core.domain.usescase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getSearchedMovies(keyword: String) = movieUseCase.getSearchedMovies(keyword).asLiveData()

}