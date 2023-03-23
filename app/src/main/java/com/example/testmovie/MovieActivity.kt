package com.example.testmovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.testmovie.ui.theme.TestMovieTheme
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCenter.start(
            application, BuildConfig.SECRET_KEY_APP_CENTER,
            Analytics::class.java, Crashes::class.java
        )

        Analytics.trackEvent("on Create Event")

        setContent {
            TestMovieTheme {
                MovieNavGraph()
            }
        }
    }
}
