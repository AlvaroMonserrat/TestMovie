package com.example.testmovie

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testmovie.movies.PopularMoviesScreen


@Composable
fun MovieNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PopularMoviesScreen
    ){
        composable(
            route = PopularMoviesScreen
        ){
            PopularMoviesScreen()
        }
    }
}

//Route
const val PopularMoviesScreen = "popularMovies"
