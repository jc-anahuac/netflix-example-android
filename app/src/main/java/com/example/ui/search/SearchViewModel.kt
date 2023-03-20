package com.example.ui.search

import androidx.lifecycle.ViewModel
import com.example.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    fun getLocalMovies() = repository.getLocalMovies()

}