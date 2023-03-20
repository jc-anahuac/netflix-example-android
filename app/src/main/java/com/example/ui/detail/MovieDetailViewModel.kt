package com.example.ui.detail

import androidx.lifecycle.ViewModel
import com.example.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    fun findById(id: Long) = movieRepository.findById(id)
}