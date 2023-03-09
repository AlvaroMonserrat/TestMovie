package com.example.testmovie.movies

import com.example.testmovie.model.PopularMovie
import com.example.testmovie.model.popularMoviesDummyList
import com.example.testmovie.source.PopularMoviesRepository
import com.example.testmovie.source.api.ApiResponseState
import org.junit.Test

class PopularMoviesViewModelTest {

    @Test
    fun getListPopularMoviesSuccess(){

        val viewModel = PopularMoviesViewModel( object: PopularMoviesRepository{
            override suspend fun getPopularMovies(): ApiResponseState<List<PopularMovie>> {
                return ApiResponseState.Success(data = popularMoviesDummyList())
            }
        })

        val status = viewModel.apiState.value

        if(status is ApiResponseState.Success){
            assert(status.data.isNotEmpty())
        }
    }

    @Test
    fun getListPopularMoviesFailure(){
        val viewModel = PopularMoviesViewModel( object: PopularMoviesRepository{
            override suspend fun getPopularMovies(): ApiResponseState<List<PopularMovie>> {
                return ApiResponseState.Error(404)
            }
        })

        val status = viewModel.apiState.value

        if(status is ApiResponseState.Error){
            assert(status.messageId == 404)
        }
    }

}