package com.evans.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.evans.movies.R
import com.evans.movies.adapter.MovieAdapter
import com.evans.movies.helpers.MovieViewModelFactory
import com.evans.movies.model.Movie
import com.evans.movies.network.MoviesApi
import com.evans.movies.repository.MovieRepository
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment(), MovieAdapter.OnItemClick {

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
        viewModel.getMovies()
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recyclerMovie.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MovieAdapter(movies, this)
            }
        })
    }

    override fun onItemClick(view: View, movie: Movie) {
        when (view.id) {
            R.id.button_book -> {
                Toast.makeText(requireContext(), "You booked ${movie.title}", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.layout_like -> {
                Toast.makeText(requireContext(), "You voted for ${movie.title}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
