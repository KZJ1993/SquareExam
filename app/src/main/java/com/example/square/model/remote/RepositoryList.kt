package com.example.square.model.remote

import com.example.square.model.local.ReposEntity

class RepositoryList : ArrayList<ReposResponse>()

fun RepositoryList.asDatabaseModel(): List<ReposEntity> {
    return map {
        ReposEntity(
            id = it.id,
            name = it.name,
            stars = it.stargazersCount.toString(),
            description = it.description,
            fork = it.forksCount.toString(),
            watcher = it.watchersCount.toString(),
        )
    }
}