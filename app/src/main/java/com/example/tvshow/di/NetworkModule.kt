package com.example.tvshow.di

import com.example.tvshow.services.TVShowAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org"

    @Provides
    @Singleton
    fun getGson(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun getRetroInstance(gson: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gson)
            .build()
    }

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): TVShowAPI {
        return retrofit.create(TVShowAPI::class.java)
    }
}