package com.evans.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evans.movies.model.Movie
import com.evans.movies.repository.MovieRepository

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    suspend fun getMovies(){
        val movies = repository.getMovies()
        _movies.value = movies
    }
}
