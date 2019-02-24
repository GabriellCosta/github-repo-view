package me.tigrao.github.repo.api

import androidx.paging.DataSource
import me.tigrao.aegis.network.NetworkClient
import me.tigrao.aegis.network.ui.UiStateLiveData
import me.tigrao.github.repo.data.ListItemVO

internal class DataSourceFactory(private val dataSourceLiveData: UiStateLiveData<Unit>) :
    DataSource.Factory<Int, ListItemVO>() {

    private val repository = RepoRepository(NetworkClient.getApi(RepoApi::class.java))

    override fun create(): DataSource<Int, ListItemVO> {
        return RepoDataSource(repository, dataSourceLiveData)
    }
}
