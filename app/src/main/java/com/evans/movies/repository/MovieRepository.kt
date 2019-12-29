package com.evans.movies.repository

import com.evans.movies.network.MoviesApi
import com.evans.movies.network.SafeApiRequest

class MovieRepository(private val api: MoviesApi) : SafeApiRequest() {

    suspend fun getMovies() = apiRequest { api.getMovies() }
}