package me.tigrao.github.repo.api

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tigrao.aegis.network.ui.UiStateLiveData
import me.tigrao.aegis.network.ui.uiAwait
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.viewmodel.RepoTransformer

private const val FIRST_PAGE = 1

internal class RepoDataSource(
    private val repository: RepoRepository,
    private val listState: UiStateLiveData<Unit>
) :
    PageKeyedDataSource<Int, ListItemVO>() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repoTransformer = RepoTransformer()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ListItemVO>
    ) {

        coroutineScope.launch {
            repository.fetchRepositories(FIRST_PAGE).uiAwait(listState) {
                val mapped = repoTransformer.map(it)

                callback.onResult(mapped, 0, nextPageCalculate(FIRST_PAGE))
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ListItemVO>
    ) {
        coroutineScope.launch {
            repository.fetchRepositories(params.key).uiAwait(listState) {
                val mapped = repoTransformer.map(it)

                callback.onResult(mapped, nextPageCalculate(params.key))
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ListItemVO>
    ) {
        //Not Implementable
    }

    private fun nextPageCalculate(currentPage: Int) = currentPage + 1
}
