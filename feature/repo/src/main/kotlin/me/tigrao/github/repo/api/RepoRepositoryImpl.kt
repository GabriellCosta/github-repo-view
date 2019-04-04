package me.tigrao.github.repo.api

internal class RepoRepositoryImpl(private val api: RepoApi) : RepoRepository {

    override fun fetchRepositories(page: Int) =
        api.fetchRepositoriesAsync("language:kotlin", "stars", page)
}