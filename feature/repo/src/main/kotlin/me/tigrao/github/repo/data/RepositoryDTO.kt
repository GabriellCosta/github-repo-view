package me.tigrao.github.repo.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class RepositoryDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: OwnerDTO,
    val forks_count: Int,
    val stargazers_count: Int
)
