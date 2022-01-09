package me.tigrao.github.repo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class RepositoryDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: OwnerDTO,
    @Json(name = "forks_count")
    val forks: Int,
    @Json(name = "stargazers_count")
    val stars: Int
)
