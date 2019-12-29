package com.evans.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.evans.movies.R
import com.evans.movies.helpers.MovieViewModelFactory
import com.evans.movies.network.MoviesApi
import com.evans.movies.repository.MovieRepository

class MovieFragment : Fragment() {

    private lateinit var factory: MovieViewModelFactory
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = MoviesApi()
        val repository = MovieRepository(api)
        factory = MovieViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
    }
}
