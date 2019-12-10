package me.tigrao.github.repo.api

import androidx.paging.DataSource
import dev.tigrao.state.StateEvent
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.data.RepoVO
import me.tigrao.github.repo.viewmodel.RepoTransformer

internal class DataSourceFactory(
    private val repository: RepoRepository,
    private val scheduler: Scheduler,
    private val repoTransformer: RepoTransformer
) :
    DataSource.Factory<Int, ListItemVO>() {

    val state = PublishSubject.create<StateEvent<RepoVO>>()

    override fun create(): DataSource<Int, ListItemVO> {
        return RepoDataSource(repository, state, scheduler, repoTransformer)
    }
}
