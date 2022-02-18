package com.example.square.data.local

import android.content.Context
import androidx.room.Room

object LocalSources {
    @Volatile
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context, dbName: String): AppDatabase =
        instance ?: synchronized(this) {
            instance ?: buildDatabase(context, dbName).also { instance = it }
        }

    private fun buildDatabase(appContext: Context, dbName: String) =
        Room.databaseBuilder(appContext, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
}

