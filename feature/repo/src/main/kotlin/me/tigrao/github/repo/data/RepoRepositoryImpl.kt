package me.tigrao.github.repo.data

import me.tigrao.github.repo.data.api.RepoApi

internal class RepoRepositoryImpl(private val api: RepoApi) : RepoRepository {

    override suspend fun fetchRepositories(page: Int) =
        api.fetchRepositoriesAsync("language:kotlin", "stars", page)
}
