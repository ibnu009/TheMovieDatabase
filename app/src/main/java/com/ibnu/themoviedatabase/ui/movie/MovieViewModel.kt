package com.ibnu.themoviedatabase.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ibnu.themoviedatabase.core.domain.usescase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovies() = movieUseCase.getAllMovies().asLiveData()



}