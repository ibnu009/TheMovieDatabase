package com.ibnu.themoviedatabase.core.utils

import com.ibnu.themoviedatabase.core.domain.model.Movie

interface RecyclerviewItemClickHandler {
    fun onClickItem(movie: Movie)
}