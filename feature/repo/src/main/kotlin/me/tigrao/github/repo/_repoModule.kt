package me.tigrao.github.repo

import me.tigrao.aegis.network.NetworkClient
import me.tigrao.github.repo.api.DataSourceFactory
import me.tigrao.github.repo.api.RepoApi
import me.tigrao.github.repo.api.RepoRepository
import me.tigrao.github.repo.api.RepoRepositoryImpl
import me.tigrao.github.repo.view.LayoutManagerFactory
import me.tigrao.github.repo.view.RepoAdapter
import me.tigrao.github.repo.viewmodel.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {

    single {
        NetworkClient.getApi(RepoApi::class.java)
    }

    single {
        RepoAdapter()
    }

    single {
        DataSourceFactory(get())
    }

    single<RepoRepository> {
        RepoRepositoryImpl(get())
    }

    viewModel {
        RepoViewModel(get())
    }

    single {
        LayoutManagerFactory()
    }
}
