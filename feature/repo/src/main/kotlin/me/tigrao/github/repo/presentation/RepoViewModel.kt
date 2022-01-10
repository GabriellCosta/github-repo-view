package me.tigrao.github.repo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.tabarato.infra.action.dispatcher.ActionDispatcher
import me.tigrao.github.repo.data.RepoDataSource
import me.tigrao.github.repo.presentation.model.RepoAction

internal class RepoViewModel(
    pagerProvider: PagerProvider,
) : ViewModel(), ActionDispatcher<RepoAction> {

    val reposPager = pagerProvider.providePager().cachedIn(viewModelScope)

    override fun dispatch(action: RepoAction) {
        TODO("Not yet implemented")
    }
}
