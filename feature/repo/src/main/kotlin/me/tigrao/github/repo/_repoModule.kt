package me.tigrao.github.repo

import dev.tigrao.state.StateMachine
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.tigrao.aegis.network.NetworkClient
import me.tigrao.github.repo.api.DataSourceFactory
import me.tigrao.github.repo.api.RepoApi
import me.tigrao.github.repo.api.RepoRepository
import me.tigrao.github.repo.api.RepoRepositoryImpl
import me.tigrao.github.repo.domain.RepoInteractor
import me.tigrao.github.repo.domain.RepoUseCase
import me.tigrao.github.repo.view.LayoutManagerFactory
import me.tigrao.github.repo.view.RepoAdapter
import me.tigrao.github.repo.viewmodel.RepoTransformer
import me.tigrao.github.repo.viewmodel.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {

    single {
        NetworkClient.getApi(RepoApi::class.java)
    }

    single {
        RepoAdapter(get())
    }

    single {
        DataSourceFactory(get(), Schedulers.io(), get())
    }

    single {
        RepoTransformer(get())
    }

    single<RepoRepository> {
        RepoRepositoryImpl(get())
    }

    viewModel {
        RepoViewModel(get(), AndroidSchedulers.mainThread())
    }

    factory {
        RepoInteractor(get(), get(), StateMachine(Schedulers.io()))
    }

    factory {
        RepoUseCase(get())
    }

    single {
        LayoutManagerFactory()
    }
}
