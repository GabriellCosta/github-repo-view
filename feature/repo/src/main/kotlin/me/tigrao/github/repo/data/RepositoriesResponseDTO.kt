package me.tigrao.github.repo.data

import com.google.gson.annotations.SerializedName

internal class RepositoriesResponseDTO(
    @SerializedName("total_count")
    val totalRepositories: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<RepositoryDTO>
)