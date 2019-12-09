package me.tigrao.github.repo.domain

import me.tigrao.github.repo.api.RepoRepository

internal class RepoUseCase(private val repository: RepoRepository) {

    fun fetchRepositories() =
        repository.fetchRepositories(0)

}