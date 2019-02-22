package me.tigrao.github.repo.api

internal class RepoRepository(private val api: RepoApi) {

    fun fetchRepositories() = api.fetchRepositories()

}