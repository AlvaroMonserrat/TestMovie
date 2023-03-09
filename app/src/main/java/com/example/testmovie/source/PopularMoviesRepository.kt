package com.example.testmovie.source

import com.example.testmovie.model.PopularMovie
import com.example.testmovie.source.api.ApiResponseState

interface PopularMoviesRepository {
    suspend fun getPopularMovies(): ApiResponseState<List<PopularMovie>>
}