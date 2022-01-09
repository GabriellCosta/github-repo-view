package me.tigrao.github.repo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import me.tigrao.github.repo.data.api.RepoDataSource

internal class RepoViewModel(
    repoDataSource: RepoDataSource,
) : ViewModel() {

    val reposPager = Pager(
        PagingConfig(pageSize = 20),
    ) {
        repoDataSource
    }.flow
        .cachedIn(viewModelScope)
}
