package me.tigrao.github.repo.data.api

import me.tigrao.github.repo.data.RepositoriesResponseDTO

internal interface RepoRepository {

    suspend fun fetchRepositories(page: Int): RepositoriesResponseDTO
}
