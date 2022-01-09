package me.tigrao.github.repo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.tabarato.infra.action.dispatcher.ActionDispatcher
import me.tigrao.github.repo.data.RepoDataSource
import me.tigrao.github.repo.presentation.model.RepoAction

private const val PAGE_SIZE = 20

internal class RepoViewModel(
    repoDataSource: RepoDataSource,
) : ViewModel(), ActionDispatcher<RepoAction> {

    val reposPager = Pager(
        PagingConfig(pageSize = PAGE_SIZE),
    ) {
        repoDataSource
    }.flow.cachedIn(viewModelScope)

    override fun dispatch(action: RepoAction) {
        TODO("Not yet implemented")
    }
}
