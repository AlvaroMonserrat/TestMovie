package com.example.testmovie.movies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmovie.model.PopularMovie
import com.example.testmovie.source.PopularMoviesRepository
import com.example.testmovie.source.api.ApiResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val repository: PopularMoviesRepository
): ViewModel() {

    var apiState = mutableStateOf<ApiResponseState<List<PopularMovie>>?>(null)
        private set

    var popularMovies = MutableStateFlow<List<PopularMovie>?>(null)
        private set

    init {
        getPopularMovies()
    }

    private fun getPopularMovies(){
        apiState.value = ApiResponseState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPopularMovies()
            withContext(Dispatchers.Main) {
                if (response is ApiResponseState.Success) {
                    updatePopularMovies(response.data)
                }
                apiState.value = response
            }
        }
    }

    private fun updatePopularMovies(list: List<PopularMovie>){
        popularMovies.value = list
    }


    fun resetApiStatus() {
        apiState.value = null
    }

    fun tryAgain() {
        getPopularMovies()
    }

}
