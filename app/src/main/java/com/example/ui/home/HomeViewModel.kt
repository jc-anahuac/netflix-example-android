package com.example.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.entities.MovieEntity
import com.example.data.repository.MovieRepository
import com.example.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    val movies = repository.getPopularMovies(Const.API_KEY)
    private val _moviesLiveData: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    val moviesLiveData: MutableLiveData<List<MovieEntity>>
        get() = _moviesLiveData

    private val _primaryMovieLiveData: MutableLiveData<MovieEntity> = MutableLiveData()
    val primaryMovieLiveData: MutableLiveData<MovieEntity>
        get() = _primaryMovieLiveData

    fun saveCacheMovies(movies: List<MovieEntity>){
        val first = movies.first().copy()
        _moviesLiveData.postValue(movies.subList(1, movies.size-1))
        _primaryMovieLiveData.postValue(first)
    }

}