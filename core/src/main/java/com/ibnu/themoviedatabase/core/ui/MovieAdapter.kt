package com.ibnu.themoviedatabase.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ibnu.themoviedatabase.core.databinding.MovieItemBinding
import com.ibnu.themoviedatabase.core.domain.model.Movie
import com.ibnu.themoviedatabase.core.utils.ConstValue
import com.ibnu.themoviedatabase.core.utils.RecyclerviewItemClickHandler

class MovieAdapter(private val onClickAction: RecyclerviewItemClickHandler) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onClickAction.onClickItem(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }


    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitleMovie.text = movie.movieName
            binding.tvCategoryMovie.text = movie.movieCategory.toString()

            Glide.with(itemView.context)
                .load("${ConstValue.POSTER_PATH}${movie.moviePosterPath}")
                .into(binding.imgCoverMovie)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.movieId == newItem.movieId && oldItem.movieId == newItem.movieId
                }

                override fun areContentsTheSame(
                    oldItem: Movie,
                    newItem: Movie
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}