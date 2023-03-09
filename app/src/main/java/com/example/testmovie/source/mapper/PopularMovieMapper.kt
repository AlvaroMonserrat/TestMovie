package com.example.testmovie.source.mapper

import com.example.testmovie.model.PopularMovie
import com.example.testmovie.source.api.PopularMovieDTO

class PopularMovieMapper {
    fun popularMoviesDTOToDomainPopularMovies(listDTO: List<PopularMovieDTO>): List<PopularMovie>{
        return listDTO.map {
            PopularMovie(
                id = it.id,
                originalTitle = it.original_title,
                overview = it.overview
            )
        }
    }
}