package com.example.square.data.local.daos

import androidx.paging.PagingSource
import androidx.room.*
import com.example.square.model.local.BookmarkEntity
import com.example.square.model.local.ReposEntity
import com.example.square.model.relation.ReposWithBookmark
import kotlinx.coroutines.flow.Flow

@Dao
interface ReposDao {
    @Transaction
    @Query("SELECT * FROM repos")
    fun getRepos(): PagingSource<Int, ReposWithBookmark>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<ReposEntity>)

    @Query("select * from repos WHERE id = :id")
    fun getReposItem(id: Int): Flow<ReposWithBookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun removeToBookmark(bookmark: BookmarkEntity)
}