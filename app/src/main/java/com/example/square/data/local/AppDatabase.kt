package com.example.square.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.square.data.local.daos.ReposDao
import com.example.square.model.local.BookmarkEntity
import com.example.square.model.local.ReposEntity

@Database(entities = [ReposEntity::class, BookmarkEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reposDao(): ReposDao
}