package me.tigrao.github.repo.data

internal class RepositoriesResponseDTO(
    val totalRepositories: Int,
    val incompleteResults: Boolean,
    val items: RepositoryDTO
)