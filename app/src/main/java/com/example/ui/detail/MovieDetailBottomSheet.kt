package com.example.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.data.entities.MovieEntity
import com.example.fragmentos.databinding.BottomSheetMovieDetailsBinding
import com.example.utils.Const
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailBottomSheet: BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetMovieDetailsBinding
    private val viewModel: MovieDetailViewModel by viewModels()

    companion object{
        const val ID = "ARGS_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.findById(arguments?.getLong(ID) ?: 0).observe(viewLifecycleOwner, ::setupUIMovie)
    }

    private fun setupUIMovie(movie: MovieEntity){
        binding.closeIcon.setOnClickListener { dismissAllowingStateLoss() }
        binding.titleText.text = movie.title
        binding.yearText.text = movie.releaseDate
        binding.overviewText.text = movie.overview
        Glide.with(this)
            .load(Const.MEDIA_URL + movie.posterPath)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(binding.posterImage)
    }
}