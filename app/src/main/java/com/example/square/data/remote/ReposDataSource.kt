package com.example.square.data.remote

import com.example.square.model.remote.RepositoryList
import com.example.square.utils.Constant
import retrofit2.http.GET

interface ReposDataSource {

    @GET(Constant.DS_PATH_GET_REPOS)
    suspend fun getRepos(): RepositoryList
}