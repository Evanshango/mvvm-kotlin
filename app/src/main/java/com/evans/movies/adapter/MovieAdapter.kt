package com.evans.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.evans.movies.R
import com.evans.movies.databinding.MovieItemBinding
import com.evans.movies.model.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val listener: OnItemClick
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.movie_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.movieItemBinding.movie = movies[position]
        holder.movieItemBinding.buttonBook.setOnClickListener {
            listener.onItemClick(holder.movieItemBinding.buttonBook, movies[position])
        }
        holder.movieItemBinding.layoutLike.setOnClickListener {
            listener.onItemClick(holder.movieItemBinding.layoutLike, movies[position])
        }
    }

    override fun getItemCount() = movies.size

    inner class MovieHolder(
        val movieItemBinding: MovieItemBinding
    ) : RecyclerView.ViewHolder(movieItemBinding.root)

    interface OnItemClick{
        fun onItemClick(view: View, movie: Movie)
    }
}