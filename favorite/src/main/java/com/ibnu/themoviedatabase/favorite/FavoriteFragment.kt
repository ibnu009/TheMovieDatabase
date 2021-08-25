package com.ibnu.themoviedatabase.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.ibnu.themoviedatabase.core.domain.model.Movie
import com.ibnu.themoviedatabase.core.ui.MovieAdapter
import com.ibnu.themoviedatabase.core.utils.RecyclerviewItemClickHandler
import com.ibnu.themoviedatabase.di.FavoriteModuleDependencies
import com.ibnu.themoviedatabase.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import timber.log.Timber
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var favoriteMovieAdapter: MovieAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val onClick = object : RecyclerviewItemClickHandler {
        override fun onClickItem(movie: Movie) {
            val action = FavoriteFragmentDirections.actionNavFavoriteToDetailMovieFragment(movie)
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
    }

    private fun initiateRecyclerview() {
        favoriteMovieAdapter = MovieAdapter(onClick)
        favoriteMovieAdapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (favoriteMovieAdapter.itemCount < 1) {
                    Timber.d("Empty!")
                } else {
                    Timber.d("Ada isinya!")
                }
            }
            binding.progressBarMovie.isVisible = loadState.refresh is LoadState.Loading
        }
        with(binding.rvMovie) {
            adapter = favoriteMovieAdapter
            layoutManager = GridLayoutManager(
                context,
                3,
                GridLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun initiateData() {
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, {
            favoriteMovieAdapter.submitData(lifecycle, it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}