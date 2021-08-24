package com.ibnu.themoviedatabase.ui.movie.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ibnu.themoviedatabase.core.data.Resource
import com.ibnu.themoviedatabase.core.domain.model.Movie
import com.ibnu.themoviedatabase.core.ui.MovieAdapter
import com.ibnu.themoviedatabase.core.utils.RecyclerviewItemClickHandler
import com.ibnu.themoviedatabase.databinding.SearchMovieFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {

    private val movieViewModel: SearchMovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    private var _binding: SearchMovieFragmentBinding? = null
    private val binding get() = _binding!!

    private val onClick = object : RecyclerviewItemClickHandler {
        override fun onClickItem(movie: Movie) {
            val action = SearchMovieFragmentDirections.actionSearchMovieFragmentToDetailMovieFragment(movie)
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchMovieFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = arguments?.let { SearchMovieFragmentArgs.fromBundle(it) }
        val keyword = safeArgs?.passKeyword?: ""

        initiateAdapter()
        getSearchedMovies(keyword)
    }

    private fun initiateAdapter() {
        movieAdapter = MovieAdapter(onClick)
        movieAdapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (movieAdapter.itemCount < 1) {
                    Timber.d("Empty!")
                } else {
                    Timber.d("Ada isinya!")
                }
            }
            binding.progressBarMovie.isVisible = loadState.refresh is LoadState.Loading
        }
        with(binding.rvMovie) {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(
                context,
                3,
                GridLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun getSearchedMovies(keyword: String) {
        movieViewModel.getSearchedMovies(keyword).observe(viewLifecycleOwner, { movie ->
            when (movie) {
                is Resource.Loading -> {
                    binding.progressBarMovie.visibility = View.VISIBLE
                    Timber.d("Loading")
                }
                is Resource.Success -> {
                    binding.progressBarMovie.visibility = View.GONE
                    movie.data?.let { movieAdapter.submitData(lifecycle, it) }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), movie.message, Toast.LENGTH_SHORT).show()
                    Timber.e("ERROR LUR, ${movie.message}")
                }
                else -> {
                    Timber.d("Check for WTH HAPPENED")
                }
            }
        })
    }

}