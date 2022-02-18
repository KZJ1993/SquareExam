package com.example.square.data.remote

import androidx.databinding.ktx.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteSources {

    private fun initOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }).build()

    fun initRetrofit(baseURL: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(initOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}