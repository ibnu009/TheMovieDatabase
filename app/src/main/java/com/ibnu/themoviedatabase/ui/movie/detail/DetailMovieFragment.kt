package com.ibnu.themoviedatabase.ui.movie.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.ibnu.themoviedatabase.R
import com.ibnu.themoviedatabase.core.domain.model.Movie
import com.ibnu.themoviedatabase.core.utils.ConstValue
import com.ibnu.themoviedatabase.databinding.DetailMovieFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private val viewModel: DetailMovieViewModel by viewModels()

    private var _binding: DetailMovieFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailMovieFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = arguments?.let { DetailMovieFragmentArgs.fromBundle(it) }
        val movie = safeArgs?.passMovie

        if (movie != null) {
            initiateDetailMovie(movie = movie)
        }
    }

    private fun initiateDetailMovie(movie: Movie) {
        Glide.with(requireContext())
            .load("${ConstValue.BACKDROP_PATH}${movie.movieBackdropPath}")
            .into(binding.imgBannerMovie)
        binding.tvMovieTitle.text = movie.movieName
        binding.tvMovieRating.text = movie.movieAverageRating.toString()
        binding.tvMovieDesc.text = movie.movieDescription

        var favoriteStatus = movie.isFavorite
        setMovieFavorite(favoriteStatus)
        binding.btnMovieFav.setOnClickListener {
            favoriteStatus = !favoriteStatus
            viewModel.setFavoriteMovie(movie, favoriteStatus)
            setMovieFavorite(favoriteStatus)
        }
    }

    private fun setMovieFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnMovieFav.setBackgroundResource(R.drawable.ic_favorited)
        } else {
            binding.btnMovieFav.setBackgroundResource(R.drawable.ic_favorite)
        }
    }

}