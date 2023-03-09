package com.example.testmovie.movies

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.testmovie.R
import com.example.testmovie.model.PopularMovie
import com.example.testmovie.model.popularMoviesDummyList
import com.example.testmovie.source.PopularMoviesRepository
import com.example.testmovie.source.api.ApiResponseState
import com.example.testmovie.ui.theme.TestMovieTheme
import org.junit.Rule
import org.junit.Test

class PopularMoviesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var repositorySuccess: PopularMoviesRepository = object : PopularMoviesRepository{
        override suspend fun getPopularMovies(): ApiResponseState<List<PopularMovie>> {
            return ApiResponseState.Success(popularMoviesDummyList())
        }
    }

    private var repositoryFailure: PopularMoviesRepository = object : PopularMoviesRepository{
        override suspend fun getPopularMovies(): ApiResponseState<List<PopularMovie>> {
            return ApiResponseState.Error(R.string.error_api_connection)
        }
    }

    private var repositoryLoading: PopularMoviesRepository = object : PopularMoviesRepository{
        override suspend fun getPopularMovies(): ApiResponseState<List<PopularMovie>> {
            return ApiResponseState.Loading()
        }
    }


    @Test
    fun titleIsShowSuccessfully() {
        setContentSuccess()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.popular_movies))
            .assertIsDisplayed()
    }

    @Test
    fun showAllListPopularMoviesSuccessfully() {
        setContentSuccess()
        val numberPopularMovies = popularMoviesDummyList().size

        composeTestRule
            .onAllNodesWithTag("card-popular-movie")
            .assertCountEquals(numberPopularMovies)

        composeTestRule
            .onAllNodesWithTag("icon-movie-description")
            .assertAll(hasClickAction())

        composeTestRule
            .onAllNodesWithTag("icon-movie-description")
            .onFirst()
            .performClick()
            .assertIsDisplayed()
    }

    @Test
    fun showDialogErrorApiConnection() {
        setContentFailure()
        composeTestRule.onNodeWithText("error-dialog")
    }

    @Test
    fun showLoadingProgressWheel() {
        setContentLoading()
        composeTestRule.onNodeWithText("loading-wheel")
    }

    private fun setContentSuccess() {
        composeTestRule.setContent {
            TestMovieTheme {
                PopularMoviesScreen(PopularMoviesViewModel(repositorySuccess))
            }
        }
    }

    private fun setContentFailure() {
        composeTestRule.setContent {
            TestMovieTheme {
                PopularMoviesScreen(PopularMoviesViewModel(repositoryFailure))
            }
        }
    }

    private fun setContentLoading() {
        composeTestRule.setContent {
            TestMovieTheme {
                PopularMoviesScreen(PopularMoviesViewModel(repositoryLoading))
            }
        }
    }
}