package com.example.square.di

import android.content.Context
import com.example.square.BuildConfig
import com.example.square.data.local.AppDatabase
import com.example.square.data.local.LocalSources
import com.example.square.data.remote.RemoteSources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RemoteSources.initRetrofit(BuildConfig.API_URL)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        LocalSources.getDatabase(appContext, BuildConfig.DB_NAME)

}