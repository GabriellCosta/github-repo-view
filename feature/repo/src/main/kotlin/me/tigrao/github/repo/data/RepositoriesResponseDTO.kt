package me.tigrao.github.repo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class RepositoriesResponseDTO(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<RepositoryDTO>,
)
