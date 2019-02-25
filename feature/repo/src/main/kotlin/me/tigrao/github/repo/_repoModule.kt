package me.tigrao.github.repo

import androidx.lifecycle.ViewModelProvider
import me.tigrao.aegis.network.NetworkClient
import me.tigrao.github.repo.api.DataSourceFactory
import me.tigrao.github.repo.api.RepoApi
import me.tigrao.github.repo.api.RepoRepository
import me.tigrao.github.repo.view.RepoAdapter
import me.tigrao.github.repo.view.ViewModelFactory
import me.tigrao.github.repo.viewmodel.RepoViewModel
import org.kodein.di.Kodein
import org.kodein.di.android.ActivityRetainedScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

val repoModule = Kodein.Module("repoModule") {

    bind<RepoApi>() with singleton {
        NetworkClient.getApi(RepoApi::class.java)
    }

    bind<RepoAdapter>() with scoped(ActivityRetainedScope).singleton {
        RepoAdapter()
    }

    bind<ViewModelProvider.Factory>() with singleton {
        ViewModelFactory(kodein)
    }

    bind<DataSourceFactory>() with singleton {
        DataSourceFactory(instance())
    }

    bind<RepoRepository>() with singleton {
        RepoRepository(instance())
    }

    bind<RepoViewModel>() with provider {
        RepoViewModel(instance())
    }
}