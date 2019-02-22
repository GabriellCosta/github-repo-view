package me.tigrao.github.repo.viewmodel

import androidx.lifecycle.ViewModel
import me.tigrao.aegis.network.NetworkClient
import me.tigrao.aegis.network.ui.UiStateLiveData
import me.tigrao.github.repo.api.RepoApi
import me.tigrao.github.repo.api.RepoRepository
import me.tigrao.github.repo.data.ListItemVO

internal class RepoViewModel : ViewModel() {

    private val repository = RepoRepository(NetworkClient.getApi(RepoApi::class.java))

    val uiState = UiStateLiveData<List<ListItemVO>>()

    fun fetchRepositories() {
        uiState.swapSource(repository.fetchRepositories()) {
            it.items.map { map ->
                ListItemVO(
                    map.owner.avatarUrl,
                    map.fullName,
                    map.stargazersCount,
                    map.forksCount,
                    map.description
                )
            }
        }
    }
}