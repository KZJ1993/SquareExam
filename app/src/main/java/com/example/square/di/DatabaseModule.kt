package com.example.square.di

import com.example.square.data.local.AppDatabase
import com.example.square.data.local.daos.ReposDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideChannelDao(database: AppDatabase): ReposDao = database.reposDao()
}