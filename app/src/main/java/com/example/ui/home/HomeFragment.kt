package com.example.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.data.entities.MovieEntity
import com.example.data.model.ResourceStatus
import com.example.fragmentos.R
import com.example.fragmentos.databinding.FragmentHomeBinding
import com.example.ui.detail.MovieDetailBottomSheet
import com.example.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(), PopularMovieItemAdapter.MovieItemListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: PopularMovieItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){
        adapter = PopularMovieItemAdapter(this)
        binding.postersList.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.primaryMovieLiveData.observe(viewLifecycleOwner, ::setupHeader)
        viewModel.moviesLiveData.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
        viewModel.movies.observe(viewLifecycleOwner) {
            when (it.resourceStatus) {
                ResourceStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.VISIBLE
                    if (!it.data.isNullOrEmpty()) viewModel.saveCacheMovies(it.data)
                }
                ResourceStatus.ERROR -> {
                    Log.e("HomeFragment", it.message.toString())
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                ResourceStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupHeader(movie: MovieEntity){
        Glide.with(this)
            .load(Const.MEDIA_URL + movie.posterPath)
            .into(binding.itemHomeHeader.backgroundImage)

        binding.itemHomeHeader.infoButton.setOnClickListener { showMovieDetail(movie.id) }
        binding.itemHomeHeader.playButton.setOnClickListener { showMovieDetail(movie.id) }
        binding.searchIcon.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_searchFragment)
        }
    }

    override fun onClickedMovie(movieId: Long) {
        showMovieDetail(movieId)
    }

    private fun showMovieDetail(movieId: Long){
        findNavController().navigate(R.id.action_home_to_movieDetailBottomSheet, args = bundleOf(
            MovieDetailBottomSheet.ID to movieId
        ))
    }
}