package me.tigrao.github.repo.domain

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import dev.tigrao.state.StateEvent
import dev.tigrao.state.StateMachine
import io.reactivex.Observable
import me.tigrao.github.repo.api.DataSourceFactory
import me.tigrao.github.repo.data.RepoVO

internal class RepoInteractor(
    private val dataSourceFactory: DataSourceFactory,
    private val repoUseCase: RepoUseCase,
    private val stateMachine: StateMachine<RepoVO>
) {

    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(true)
        .build()

    fun fetchRepos(): Observable<StateEvent<RepoVO>> {

        val compose = RxPagedListBuilder(dataSourceFactory, config)
            .buildObservable()
            .map {
                RepoVO(it)
            }

            .compose(stateMachine)

        return dataSourceFactory.state.mergeWith(compose)
    }
}