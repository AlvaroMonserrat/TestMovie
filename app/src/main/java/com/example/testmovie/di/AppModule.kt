package com.example.testmovie.di

import com.example.testmovie.source.PopularMoviesRepository
import com.example.testmovie.source.PopularMoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindPopularMoviesRepository(repository: PopularMoviesRepositoryImpl): PopularMoviesRepository
}