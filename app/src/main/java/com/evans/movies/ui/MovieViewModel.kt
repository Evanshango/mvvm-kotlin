package com.evans.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evans.movies.helpers.Coroutines
import com.evans.movies.model.Movie
import com.evans.movies.repository.MovieRepository
import kotlinx.coroutines.Job

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private lateinit var job: Job
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun getMovies() {
        job = Coroutines.ioThenMain(
            { repository.getMovies() },
            {_movies.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
