package com.example.testmovie.movies

import com.example.testmovie.source.PopularMoviesRepositoryImpl
import com.example.testmovie.source.api.ApiServiceMovie
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PopularMoviesRepositoryTest {

    private val apiServiceMovie: ApiServiceMovie = mockk(relaxed = true)

    @Test
    fun getListPopularMoviesFromApiService() = runBlocking{
        val repository = PopularMoviesRepositoryImpl(apiServiceMovie)
        repository.getPopularMovies()
        coVerify {
            apiServiceMovie.getPopularMovies()
        }
    }
}