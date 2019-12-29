package com.evans.movies.helpers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evans.movies.repository.MovieRepository
import com.evans.movies.ui.MovieViewModel

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val repository: MovieRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}