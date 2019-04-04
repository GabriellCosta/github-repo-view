package me.tigrao.github.repo.api

import kotlinx.coroutines.Deferred
import me.tigrao.github.repo.data.RepositoriesResponseDTO

internal interface RepoRepository {

    fun fetchRepositories(page: Int): Deferred<RepositoriesResponseDTO>
}
