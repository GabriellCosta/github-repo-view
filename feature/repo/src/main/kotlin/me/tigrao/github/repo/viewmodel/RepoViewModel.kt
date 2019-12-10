package me.tigrao.github.repo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.tigrao.state.StateEvent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import me.tigrao.github.repo.data.RepoVO
import me.tigrao.github.repo.domain.RepoInteractor

internal class RepoViewModel(
    private val repoInteractor: RepoInteractor,
    private val schedulers: Scheduler
) : ViewModel() {

    private val _viewState = MutableLiveData<StateEvent<RepoVO>>()
    val viewState: LiveData<StateEvent<RepoVO>> = _viewState

    private var disposable = CompositeDisposable()

    fun fetchRepositories() {
        repoInteractor.fetchRepos()
            .observeOn(schedulers)
            .subscribe {
                _viewState.value = it
            }.addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}
