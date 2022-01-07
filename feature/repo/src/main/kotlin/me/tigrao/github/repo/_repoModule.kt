package me.tigrao.github.repo

import me.tigrao.github.repo.api.RepoApi
import me.tigrao.github.repo.api.RepoDataSource
import me.tigrao.github.repo.api.RepoRepository
import me.tigrao.github.repo.api.RepoRepositoryImpl
import me.tigrao.github.repo.view.LayoutManagerFactory
import me.tigrao.github.repo.view.RepoAdapter
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
