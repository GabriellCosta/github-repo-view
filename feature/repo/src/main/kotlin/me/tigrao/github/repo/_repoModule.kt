package me.tigrao.github.repo

import me.tigrao.github.repo.data.api.RepoApi
import me.tigrao.github.repo.data.RepoDataSource
import me.tigrao.github.repo.data.RepoRepository
import me.tigrao.github.repo.data.RepoRepositoryImpl
import me.tigrao.github.repo.view.adapter.LayoutManagerFactory
import me.tigrao.github.repo.view.adapter.RepoAdapter
import me.tigrao.github.repo.viewmodel.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val repoModule = module {

    single {
        get<Retrofit>().create(RepoApi::class.java)
    }

    single {
        RepoAdapter()
    }

    single {
        RepoDataSource(get())
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
