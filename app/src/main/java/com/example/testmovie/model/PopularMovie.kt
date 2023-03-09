package com.example.testmovie.model

data class PopularMovie(
    val id: Int,
    val originalTitle: String,
    val overview: String
)

fun popularMovieDummy(
    id: Int = 1,
    originalTitle: String = "Example",
    overview: String = "Overview test"
) = PopularMovie(id, originalTitle, "Overview test")

fun popularMoviesDummyList() = listOf(
    popularMovieDummy(1),
    popularMovieDummy(2),
    popularMovieDummy(3),
    popularMovieDummy(4)
)