package com.example.testmovie.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testmovie.R
import com.example.testmovie.model.PopularMovie
import com.example.testmovie.model.popularMoviesDummyList
import com.example.testmovie.ui.componets.CardMovie
import com.example.testmovie.ui.componets.LoadingErrorBox
import com.example.testmovie.ui.theme.MidnightBlue
import com.example.testmovie.ui.theme.TestMovieTheme

@Composable
fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel()
){
    val popularMovies = viewModel.popularMovies
        .collectAsStateWithLifecycle()

    PopularMoviesScreen(
        popularMovies = popularMovies.value ?: listOf(),
        onTryAgain = viewModel::tryAgain
    )

    LoadingErrorBox(
        state = viewModel.apiState.value,
        onResetStatus = viewModel::resetApiStatus
    )
}



@Composable
private fun PopularMoviesScreen(
    popularMovies: List<PopularMovie>,
    onTryAgain: ()->Unit
){
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 4.dp),
            text = stringResource(id = R.string.popular_movies),
            fontWeight = FontWeight.Medium,
            color = MidnightBlue,
            textAlign = TextAlign.Center
        )

        if (popularMovies.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(popularMovies){
                    CardMovie(it)
                }
            }
        }else{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = onTryAgain) {
                    Text(text = stringResource(id = R.string.try_again))
                }
            }
        }
    }
}

@Composable
@Preview
fun PopularMoviesScreenPreview(){
    TestMovieTheme() {
        Surface() {
            PopularMoviesScreen(popularMoviesDummyList()){}
        }
    }
}