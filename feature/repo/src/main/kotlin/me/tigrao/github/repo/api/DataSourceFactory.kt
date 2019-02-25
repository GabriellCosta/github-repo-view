package me.tigrao.github.repo.api

import androidx.paging.DataSource
import me.tigrao.aegis.network.ui.UiStateLiveData
import me.tigrao.github.repo.data.ListItemVO

internal class DataSourceFactory(private val repository: RepoRepository) :
    DataSource.Factory<Int, ListItemVO>() {

    val dataSourceLiveData = UiStateLiveData<Unit>()

    override fun create(): DataSource<Int, ListItemVO> {
        return RepoDataSource(repository, dataSourceLiveData)
    }
}
