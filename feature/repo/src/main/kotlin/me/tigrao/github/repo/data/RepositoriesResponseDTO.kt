package me.tigrao.github.repo.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class RepositoriesResponseDTO(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepositoryDTO>,
)
