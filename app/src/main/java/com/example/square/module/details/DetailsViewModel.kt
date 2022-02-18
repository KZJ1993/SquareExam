package com.example.square.module.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.square.data.repository.ReposRepository
import com.example.square.model.local.BookmarkEntity
import com.example.square.model.relation.ReposWithBookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val reposRepository: ReposRepository
) : ViewModel(), LifecycleObserver {

    val errorFlow = reposRepository.errorFlow

    fun getRepoItem(id: Int) = reposRepository.getRepoItem(id)

    fun updateRepos(data: ReposWithBookmark) {
        viewModelScope.launch(Dispatchers.IO) {
            if (data.bookmark != null) {
                reposRepository.removeToBookmark(data.bookmark)
            } else {
                reposRepository.addToBookmark(BookmarkEntity(data.repos.id))
            }
        }
    }
}