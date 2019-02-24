package me.tigrao.github.repo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.tigrao.aegis.network.ui.UiStateLiveData
import me.tigrao.github.repo.api.DataSourceFactory
import me.tigrao.github.repo.data.ListItemVO

private const val PAGE_SIZE = 3

internal class RepoViewModel : ViewModel() {

    val uiState = UiStateLiveData<Unit>()
    private val factory = DataSourceFactory(uiState)

    fun fetchRepositories(): LiveData<PagedList<ListItemVO>> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(factory, config).build()
    }
}