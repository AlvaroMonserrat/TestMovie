package com.example.testmovie.source

import com.example.testmovie.model.PopularMovie
import com.example.testmovie.source.api.ApiResponseState
import com.example.testmovie.source.api.ApiServiceMovie
import com.example.testmovie.source.api.makeNetworkCall
import com.example.testmovie.source.mapper.PopularMovieMapper
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val apiServiceMovie: ApiServiceMovie
) : PopularMoviesRepository {

    override suspend fun getPopularMovies(): ApiResponseState<List<PopularMovie>> {
        return makeNetworkCall {
            val response = apiServiceMovie.getPopularMovies()
            val mapper = PopularMovieMapper()
            mapper.popularMoviesDTOToDomainPopularMovies(response.results)
        }
    }

}