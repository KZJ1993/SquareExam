package com.example.square.di

import com.example.square.data.remote.ReposDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDataSource(retrofit: Retrofit): ReposDataSource =
        retrofit.create(ReposDataSource::class.java)
}