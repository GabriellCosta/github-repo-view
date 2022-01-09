package me.tigrao.github.repo.data

import me.tigrao.github.repo.data.api.model.RepositoriesResponse

internal interface RepoRepository {

    suspend fun fetchRepositories(page: Int): RepositoriesResponse
}
