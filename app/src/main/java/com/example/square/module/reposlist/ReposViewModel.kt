package com.example.square.module.reposlist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.square.data.repository.ReposRepository
import com.example.square.model.relation.ReposWithBookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class ReposViewModel @Inject constructor(
    private val reposRepository: ReposRepository
) : ViewModel(), LifecycleObserver {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            reposRepository.refreshReposList()
        }
    }

    val allRepos: Flow<PagingData<ReposWithBookmark>> =
        Pager(PagingConfig(15, enablePlaceholders = true)) { reposRepository.getRepos() }
            .flow.map { pagingData -> pagingData.map { it } }.cachedIn(viewModelScope)

    val errorFlow = reposRepository.errorFlow
}