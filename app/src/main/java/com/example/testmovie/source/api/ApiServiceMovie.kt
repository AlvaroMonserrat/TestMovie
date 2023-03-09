package com.example.testmovie.source.api


import retrofit2.http.GET


interface ApiServiceMovie {
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviesDTO
}