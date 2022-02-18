package com.example.square.data.repository

import androidx.paging.ExperimentalPagingApi
import com.example.square.data.local.daos.ReposDao
import com.example.square.data.remote.ReposDataSource
import com.example.square.model.local.BookmarkEntity
import com.example.square.model.remote.asDatabaseModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@ExperimentalPagingApi
class ReposRepository @Inject constructor(
    private val apiService: ReposDataSource,
    private val reposDao: ReposDao
) {

    private val errorChannel = Channel<String?>()
    val errorFlow = errorChannel.receiveAsFlow()

    suspend fun refreshReposList() {
        try {
            val list = apiService.getRepos()
            reposDao.insertAll(list.asDatabaseModel())
        } catch (e: Exception) {
            errorChannel.send(e.message)
        }
    }

    fun getRepos() = reposDao.getRepos()

    fun getRepoItem(id: Int) = reposDao.getReposItem(id)

    fun addToBookmark(item: BookmarkEntity) = reposDao.addToBookmark(item)

    suspend fun removeToBookmark(item: BookmarkEntity) = reposDao.removeToBookmark(item)
}