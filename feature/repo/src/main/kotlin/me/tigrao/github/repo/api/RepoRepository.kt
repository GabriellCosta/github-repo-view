package me.tigrao.github.repo.api

import me.tigrao.github.repo.data.RepositoriesResponseDTO

internal interface RepoRepository {

    suspend fun fetchRepositories(page: Int): RepositoriesResponseDTO
}
