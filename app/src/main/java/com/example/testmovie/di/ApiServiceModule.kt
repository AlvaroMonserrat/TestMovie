package com.example.testmovie.di

import com.example.testmovie.BuildConfig
import com.example.testmovie.source.api.ApiServiceMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {


    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServiceMovie
            = retrofit.create(ApiServiceMovie::class.java)

    @Provides
    fun provideMovieRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor{ apiKeyAsQuery(it)}
            .build()
    }
}


private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain.request()
        .newBuilder()
        .url(chain.request().url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY).build())
        .build()

)

