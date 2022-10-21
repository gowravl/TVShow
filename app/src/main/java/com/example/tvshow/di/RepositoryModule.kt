package com.example.tvshow.di

import com.example.tvshow.repository.MyRepository
import com.example.tvshow.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun provideMainRepositoryImpl(repository: MyRepository) : Repository
}