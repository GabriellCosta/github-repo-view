package me.tigrao.github.repo.data

internal class RepositoryDTO(
    val id: Long,
    val fullName: String,
    val description: String,
    val owner: OwnerDTO,
    val forksCount: Int,
    val stargazersCount: Int
)
