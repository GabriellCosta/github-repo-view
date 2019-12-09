package me.tigrao.github.repo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.tigrao.state.StateEvent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import me.tigrao.github.repo.api.DataSourceFactory
import me.tigrao.github.repo.data.ListItemVO
import me.tigrao.github.repo.data.RepoVO
import me.tigrao.github.repo.domain.RepoInteractor


internal class RepoViewModel(
    private val repoInteractor: RepoInteractor,
    private val schedulers: Scheduler) : ViewModel() {

    private val _viewState = MutableLiveData<StateEvent<RepoVO>>()
    val viewState: LiveData<StateEvent<RepoVO>> = _viewState

    private var disposable: Disposable? = null

    fun fetchRepositories() {
        disposable = repoInteractor.fetchRepos()
            .observeOn(schedulers)
            .subscribe {
                _viewState.value = it
            }
    }

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }
}
