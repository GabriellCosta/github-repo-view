package me.tigrao.github.repo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.tigrao.aegis.network.ui.UiStateLiveData
import me.tigrao.github.repo.api.DataSourceFactory
import me.tigrao.github.repo.data.ListItemVO

private const val PAGE_SIZE = 3

internal class RepoViewModel(private val factory: DataSourceFactory) : ViewModel() {

    val uiState = factory.dataSourceLiveData
    private val pagedLiveData: LiveData<PagedList<ListItemVO>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
        pagedLiveData = LivePagedListBuilder(factory, config).build()
    }

    fun fetchRepositories(): LiveData<PagedList<ListItemVO>> {
        return pagedLiveData
    }
}