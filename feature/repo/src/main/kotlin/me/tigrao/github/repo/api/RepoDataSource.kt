package me.tigrao.github.repo.api

import androidx.paging.PageKeyedDataSource
import dev.tigrao.router.Routes
import dev.tigrao.state.FinishedEvent
import dev.tigrao.state.StartedEvent
import dev.tigrao.state.StateEvent
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.data.RepoVO
import me.tigrao.github.repo.viewmodel.RepoTransformer

private const val FIRST_PAGE = 1

internal class RepoDataSource(
    private val repository: RepoRepository,
    private val state: PublishSubject<StateEvent<RepoVO>>,
    private val schedulers: Scheduler,
    private val repoTransformer: RepoTransformer
) :
    PageKeyedDataSource<Int, ListItemVO>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ListItemVO>
    ) {
        buildStream(FIRST_PAGE)
            .subscribe {
                callback.onResult(it, 0, nextPageCalculate(FIRST_PAGE))
            }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ListItemVO>
    ) {

        buildStream(params.key)
            .subscribe {
                callback.onResult(it, nextPageCalculate(params.key))
            }
    }

    private fun buildStream(key: Int) =
        repository.fetchRepositories(key)
            .observeOn(schedulers)
            .subscribeOn(schedulers)
            .map {
                repoTransformer.map(it)
            }
            .doOnSubscribe {
                state.onNext(StartedEvent)
            }
            .doOnComplete {
                state.onNext(FinishedEvent)
            }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ListItemVO>
    ) {
        //Not Implementable
    }

    private fun nextPageCalculate(currentPage: Int) = currentPage + 1
}
